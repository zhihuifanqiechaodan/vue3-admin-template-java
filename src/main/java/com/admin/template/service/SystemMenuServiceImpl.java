package com.admin.template.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
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
import com.admin.template.request.MenuReqVo;
import com.admin.template.request.MenuSortReqVo;
import com.admin.template.utils.CollectionUtils;
import com.admin.template.utils.ThreadLocalUtil;
import com.admin.template.vo.SystemMenuSvcVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

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
     * @return
     */
    public List<SystemMenuSvcVo> getMenuList() {
        Integer userId = ThreadLocalUtil.getUserId("userId");
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
     * @param reqVo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer addMenu(AddMenuReqVo reqVo) {
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

        Integer userId = ThreadLocalUtil.getUserId("userId");
        SystemMenuDo systemMenuDo = new SystemMenuDo();
        BeanUtil.copyProperties(reqVo, systemMenuDo);
        systemMenuDo.setCreator(userId);
        systemMenuDo.setUpdater(userId);
        systemMenuDo.setCataloguePath(IdUtil.fastSimpleUUID());
        systemMenuDao.insertSelective(systemMenuDo);
        List<ButtonPermissions> buttonPermissionsList = reqVo.getButtonPermissions() == null ? Collections.EMPTY_LIST : reqVo.getButtonPermissions();
        for (ButtonPermissions buttonPermissions : buttonPermissionsList) {
            SystemMenuDo menuDo = new SystemMenuDo();
            menuDo.setParentId(systemMenuDo.getId());
            menuDo.setButtonId(buttonPermissions.getValue());
            menuDo.setType(MenuTypeEnum.BUTTON.getType());
            menuDo.setTitle(buttonPermissions.getLabel());
            menuDo.setCreator(userId);
            menuDo.setUpdater(userId);
            systemMenuDao.insertSelective(menuDo);
        }
        return 1;
    }

    /**
     * 编辑菜单
     *
     * @param reqVo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer updateMenu(AddMenuReqVo reqVo) {
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
        Integer userId = ThreadLocalUtil.getUserId("userId");
        //更新菜单、目录
        SystemMenuDo systemMenuDo = new SystemMenuDo();
        BeanUtil.copyProperties(reqVo, systemMenuDo);
        systemMenuDo.setUpdater(userId);
        systemMenuDao.updateSelective(systemMenuDo);

        if (reqVo.getType().equals(MenuTypeEnum.MENU.getType())) {
            SystemMenuSvcBean svcBean = new SystemMenuSvcBean();
            svcBean.setParentId(reqVo.getId());
            svcBean.setDeleted(0);
            List<SystemMenuDo> dbMenuList = systemMenuDao.queryAllByLimit(svcBean);
            List<Integer> dbButtonIds = CollectionUtils.convertList(dbMenuList, SystemMenuDo::getId);

            List<ButtonPermissions> buttonPermissions = reqVo.getButtonPermissions() == null ? Collections.emptyList() : reqVo.getButtonPermissions();
            List<Integer> reqButtons = CollectionUtils.convertList(buttonPermissions, ButtonPermissions::getId);

            Set<String> labelList = CollectionUtils.convertSet(buttonPermissions, ButtonPermissions::getLabel);
            if (buttonPermissions.size() != labelList.size()) {
                throw ServiceExceptionUtil.exception(ErrorCodeConstants.BUTTON_TITLE_EXIST_ERROR);
            }
            Set<Integer> valueList = CollectionUtils.convertSet(buttonPermissions, ButtonPermissions::getValue);
            if (buttonPermissions.size() != valueList.size()) {
                throw ServiceExceptionUtil.exception(ErrorCodeConstants.BUTTON_ID_EXIST_ERROR);
            }

            Collection<Integer> delButtons = CollUtil.subtract(dbButtonIds, reqButtons);
            Collection<Integer> addButtons = CollUtil.subtract(reqButtons, dbButtonIds);
            Collection<Integer> updateButtons = CollUtil.intersection(reqButtons, dbButtonIds);

            Map<Integer, ButtonPermissions> buttonMap = CollectionUtils.convertMap(buttonPermissions, ButtonPermissions::getId);
            for (Integer buttonId : delButtons) {
                systemMenuDao.deleteById(buttonId);
            }
            for (Integer buttonId : addButtons) {
                ButtonPermissions permissions = buttonMap.get(buttonId);
                SystemMenuDo menuDo = new SystemMenuDo();
                menuDo.setType(MenuTypeEnum.BUTTON.getType());
                menuDo.setParentId(reqVo.getId());
                menuDo.setButtonId(permissions.getValue());
                menuDo.setTitle(permissions.getLabel());
                menuDo.setCreator(userId);
                menuDo.setUpdater(userId);
                systemMenuDao.insertSelective(systemMenuDo);
            }
            for (Integer buttonId : updateButtons) {
                ButtonPermissions permissions = buttonMap.get(buttonId);
                SystemMenuDo menuDo = new SystemMenuDo();
                menuDo.setId(buttonId);
                menuDo.setButtonId(permissions.getValue());
                menuDo.setTitle(permissions.getLabel());
                menuDo.setUpdater(userId);
                systemMenuDao.updateSelective(menuDo);
            }
        }
        return 1;
    }

    /**
     * 菜单排序
     *
     * @param reqVo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer menuSort(MenuReqVo reqVo) {
        List<MenuSortReqVo> menuSortReqVoList = reqVo.getSortList();
        Integer userId = ThreadLocalUtil.getUserId("userId");
        for (MenuSortReqVo item : menuSortReqVoList) {
            SystemMenuDo systemMenuDo = new SystemMenuDo();
            systemMenuDo.setId(item.getMenuId());
            systemMenuDo.setParentId(item.getParentId() == null ? 0 : item.getParentId());
            systemMenuDo.setSort(item.getSort());
            systemMenuDo.setUpdater(userId);
            systemMenuDao.updateSelective(systemMenuDo);
        }
        return 1;
    }
}
