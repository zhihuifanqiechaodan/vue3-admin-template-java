package com.admin.template.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import com.admin.template.bean.SystemMenuSvcBean;
import com.admin.template.bean.SystemUserMenuSvcBean;
import com.admin.template.dao.SystemMenuDao;
import com.admin.template.dao.SystemRoleDao;
import com.admin.template.dao.SystemUserDao;
import com.admin.template.dao.SystemUserMenuDao;
import com.admin.template.domain.SystemMenuDo;
import com.admin.template.domain.SystemRoleDo;
import com.admin.template.domain.SystemUserDo;
import com.admin.template.domain.SystemUserMenuDo;
import com.admin.template.enums.MenuTypeEnum;
import com.admin.template.enums.RoleTypeEnum;
import com.admin.template.exception.ErrorCodeConstants;
import com.admin.template.exception.ServiceExceptionUtil;
import com.admin.template.request.AddMenuReqVo;
import com.admin.template.request.ButtonPermissions;
import com.admin.template.request.MenuSortReqVo;
import com.admin.template.utils.CollectionUtils;
import com.admin.template.vo.SystemMenuSvcVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @className: SystemServiceImpl
 * @description:
 * @author: YangQian
 * @date: 2023/9/22 14:42
 */
@Service
public class SystemMenuServiceImpl {

    @Resource
    private SystemUserDao systemUserDao;
    @Resource
    private SystemRoleDao systemRoleDao;
    @Resource
    private SystemMenuDao systemMenuDao;
    @Resource
    private SystemUserMenuDao systemUserMenuDao;

    /**
     * 获取导航栏菜单列表
     *
     * @param userId
     * @return
     */
    public List<SystemMenuSvcVo> getMenuList(int userId) {
        SystemUserDo systemUserDo = systemUserDao.queryById(userId);
        SystemRoleDo systemRoleDo = systemRoleDao.queryById(systemUserDo.getRoleId());
        //超级管理员
        if (systemRoleDo.getType().equals(RoleTypeEnum.supers.getType())) {
            SystemMenuSvcBean svcBean = new SystemMenuSvcBean();
            svcBean.setDeleted(0);
            List<SystemMenuDo> systemMenuDos = systemMenuDao.queryAllByLimit(svcBean);
            return tranFormSystemMenuDoList(systemMenuDos);
        }
        //普通用户
        SystemUserMenuSvcBean svcBean = new SystemUserMenuSvcBean();
        svcBean.setDeleted(0);
        List<SystemUserMenuDo> systemUserMenuDos = systemUserMenuDao.queryAllByLimit(svcBean);
        List<Integer> menuIds = CollectionUtils.convertList(systemUserMenuDos, SystemUserMenuDo::getMenuId);
        List<SystemMenuDo> systemMenuDos = menuIds.size() > 0 ? systemMenuDao.queryByIds(menuIds) : Collections.EMPTY_LIST;
        return tranFormSystemMenuDoList(systemMenuDos);
    }

    public List<SystemMenuSvcVo> tranFormSystemMenuDoList(List<SystemMenuDo> systemMenuDos) {
        List<SystemMenuSvcVo> svcVos = new ArrayList<>();
        for (SystemMenuDo systemMenuDo : systemMenuDos) {
            SystemMenuSvcVo svcVo = new SystemMenuSvcVo();
            BeanUtil.copyProperties(systemMenuDo, svcVo);
            svcVo.setHidden(systemMenuDo.getHidden() == 1 ? true : false);
            svcVo.setAuth(systemMenuDo.getAuth() == 1 ? true : false);
            svcVo.setShow(systemMenuDo.getShow() == 1 ? true : false);
            svcVo.setCache(systemMenuDo.getCache() == 1 ? true : false);
            svcVo.setBreadcrumb(systemMenuDo.getBreadcrumb() == 1 ? true : false);
            svcVo.setAffix(systemMenuDo.getAffix() == 1 ? true : false);
            svcVos.add(svcVo);
        }
        return svcVos;
    }

    /**
     * 获取菜单列表
     *
     * @return
     */
    public List<SystemMenuSvcVo> getAllMenuList() {
        SystemMenuSvcBean svcBean = new SystemMenuSvcBean();
        svcBean.setDeleted(0);
        List<SystemMenuDo> systemMenuDos = systemMenuDao.queryAllByLimit(svcBean);
        return tranFormSystemMenuDoList(systemMenuDos);
    }

    /**
     * 新建菜单
     *
     * @param userId
     * @param reqVo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer addMenu(int userId, AddMenuReqVo reqVo) {
        if (reqVo.getType().equals(MenuTypeEnum.CATALOGUE.getType())) {
            if (reqVo.getShow() == null || reqVo.getLayout() == null) {
                throw ServiceExceptionUtil.exception(ErrorCodeConstants.BAD_REQUEST);
            }
        }
        if (reqVo.getType().equals(MenuTypeEnum.MENU.getType())) {
            if (reqVo.getCache() == null || reqVo.getBreadcrumb() == null || reqVo.getAffix() == null || reqVo.getPath() == null) {
                throw ServiceExceptionUtil.exception(ErrorCodeConstants.BAD_REQUEST);
            }

            SystemMenuSvcBean svcBean = new SystemMenuSvcBean();
            svcBean.setPath(reqVo.getPath());
            List<SystemMenuDo> systemMenuDos = systemMenuDao.queryAllByLimit(svcBean);
            if (systemMenuDos != null && systemMenuDos.size() > 0) {
                throw ServiceExceptionUtil.exception(ErrorCodeConstants.PATH_EXIST_ERROR);
            }
        }

        SystemMenuSvcBean svcBean = new SystemMenuSvcBean();
        svcBean.setTitle(reqVo.getTitle());
        List<SystemMenuDo> systemMenuDos = systemMenuDao.queryAllByLimit(svcBean);
        if (systemMenuDos != null && systemMenuDos.size() > 0) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.TITLE_EXIST_ERROR);
        }

        SystemMenuDo systemMenuDo = new SystemMenuDo();
        BeanUtil.copyProperties(reqVo, systemMenuDo);
        systemMenuDo.setCreator(userId);
        systemMenuDo.setUpdater(userId);
        systemMenuDo.setCataloguePath(IdUtil.fastSimpleUUID());
        systemMenuDao.insertSelective(systemMenuDo);
        for (ButtonPermissions buttonPermissions : reqVo.getButtonPermissions()) {
            SystemMenuDo menuDo = new SystemMenuDo();
            menuDo.setParentId(systemMenuDo.getId());
            menuDo.setButtonId(buttonPermissions.getValue());
            menuDo.setType(MenuTypeEnum.BUTTON.getType());
            menuDo.setTitle(buttonPermissions.getLabel());
            menuDo.setCreator(userId);
            menuDo.setUpdater(userId);
            systemMenuDao.insertSelective(systemMenuDo);
        }
        return 1;
    }

    public Integer updateMenu(int userId, AddMenuReqVo reqVo) {
        if (reqVo.getId() == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.BAD_REQUEST, "Id不能为空");
        }
        if (reqVo.getType().equals(MenuTypeEnum.CATALOGUE.getType())) {
            if (reqVo.getShow() == null || reqVo.getLayout() == null) {
                throw ServiceExceptionUtil.exception(ErrorCodeConstants.BAD_REQUEST);
            }
        }
        if (reqVo.getType().equals(MenuTypeEnum.MENU.getType())) {
            if (reqVo.getCache() == null || reqVo.getBreadcrumb() == null || reqVo.getAffix() == null || reqVo.getPath() == null) {
                throw ServiceExceptionUtil.exception(ErrorCodeConstants.BAD_REQUEST);
            }
        }
        //更新菜单、目录
        SystemMenuDo systemMenuDo = new SystemMenuDo();
        BeanUtil.copyProperties(reqVo, systemMenuDo);
        systemMenuDo.setUpdater(userId);
        systemMenuDao.updateSelective(systemMenuDo);
        //删除菜单目录关联下的button
        systemMenuDao.deleteByParentId(reqVo.getId());
        for (ButtonPermissions buttonPermissions : reqVo.getButtonPermissions()) {
            SystemMenuDo menuDo = new SystemMenuDo();
            menuDo.setParentId(reqVo.getId());
            menuDo.setButtonId(buttonPermissions.getValue());
            menuDo.setType(MenuTypeEnum.BUTTON.getType());
            menuDo.setTitle(buttonPermissions.getLabel());
            menuDo.setCreator(userId);
            menuDo.setUpdater(userId);
            systemMenuDao.insertSelective(systemMenuDo);
        }
        return 1;
    }

    /**
     * 菜单排序
     *
     * @param userId
     * @param reqVo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer menuSort(int userId, MenuSortReqVo reqVo) {
        int sort = 0;
        for (Integer menuId : reqVo.getMenuIds()) {
            SystemMenuDo systemMenuDo = new SystemMenuDo();
            systemMenuDo.setId(menuId);
            systemMenuDo.setSort(sort);
            systemMenuDo.setUpdater(userId);
            systemMenuDao.updateSelective(systemMenuDo);
            sort++;
        }
        return 1;
    }
}
