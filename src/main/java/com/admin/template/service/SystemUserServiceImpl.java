package com.admin.template.service;

import cn.hutool.core.bean.BeanUtil;
import com.admin.template.bean.SystemRoleSvcBean;
import com.admin.template.bean.SystemUserSvcBean;
import com.admin.template.dao.*;
import com.admin.template.domain.SystemRoleDo;
import com.admin.template.domain.SystemRoleMenuDo;
import com.admin.template.domain.SystemUserDo;
import com.admin.template.domain.SystemUserMenuDo;
import com.admin.template.exception.ErrorCodeConstants;
import com.admin.template.exception.ServiceExceptionUtil;
import com.admin.template.request.AddUserReqVo;
import com.admin.template.request.UpdatePasswoedReqVo;
import com.admin.template.request.UpdateUserMenuReqVo;
import com.admin.template.request.UpdateUserReqVo;
import com.admin.template.response.UserListRespVo;
import com.admin.template.utils.CollectionUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @className: SystemUserServiceImpl
 * @description:
 * @author: YangQian
 * @date: 2023/9/25 16:20
 */
@Service
public class SystemUserServiceImpl {

    @Resource
    private SystemUserDao systemUserDao;
    @Resource
    private SystemRoleDao systemRoleDao;
    @Resource
    private SystemMenuDao systemMenuDao;
    @Resource
    private SystemUserMenuDao systemUserMenuDao;
    @Resource
    private SystemRoleMenuDao systemRoleMenuDao;
    @Resource
    private PasswordEncoder passwordEncoder;

    /**
     * 新增用户
     *
     * @param reqVo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer addUser(int userId, AddUserReqVo reqVo) {
        List<SystemRoleMenuDo> systemRoleMenuDos = systemRoleMenuDao.queryByRoleId(reqVo.getRoleId());
        if (systemRoleMenuDos == null || systemRoleMenuDos.size() == 0) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.NO_ROLE_MENU_ERROR);
        }
        //新增用户
        SystemUserDo systemUserDo = new SystemUserDo();
        BeanUtil.copyProperties(reqVo, systemUserDo);
        systemUserDo.setCreator(userId);
        systemUserDo.setUpdater(userId);
        systemUserDao.insertSelective(systemUserDo);
        //创建用户菜单
        List<Integer> menuIds = CollectionUtils.convertList(systemRoleMenuDos, SystemRoleMenuDo::getMenuId);
        for (Integer menuId : menuIds) {
            SystemUserMenuDo systemUserMenuDo = new SystemUserMenuDo();
            systemUserMenuDo.setUserId(userId);
            systemUserMenuDo.setMenuId(menuId);
            systemUserMenuDo.setCreator(userId);
            systemUserMenuDo.setUpdater(userId);
            systemUserMenuDao.insertSelective(systemUserMenuDo);
        }
        return systemUserDo.getId();
    }

    /**
     * 编辑用户
     *
     * @param userId
     * @param reqVo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer updateUser(int userId, UpdateUserReqVo reqVo) {
        SystemUserDo userDo = systemUserDao.queryById(reqVo.getUserId());
        SystemUserDo systemUserDo = new SystemUserDo();
        BeanUtil.copyProperties(reqVo, systemUserDo);
        systemUserDo.setId(reqVo.getUserId());
        systemUserDo.setUpdater(userId);
        systemUserDao.updateSelective(systemUserDo);
        //角色发生变更
        if (!userDo.getRoleId().equals(reqVo.getRoleId())) {
            //删除用户原来的菜单权限
            systemUserMenuDao.deleteByUserId(reqVo.getUserId());
            //新增用户菜单权限
            List<SystemRoleMenuDo> systemRoleMenuDos = systemRoleMenuDao.queryByRoleId(reqVo.getRoleId());
            List<Integer> menuIds = CollectionUtils.convertList(systemRoleMenuDos, SystemRoleMenuDo::getMenuId);
            for (Integer menuId : menuIds) {
                SystemUserMenuDo systemUserMenuDo = new SystemUserMenuDo();
                systemUserMenuDo.setUserId(reqVo.getUserId());
                systemUserMenuDo.setMenuId(menuId);
                systemUserMenuDo.setCreator(userId);
                systemUserMenuDo.setUpdater(userId);
                systemUserMenuDao.insertSelective(systemUserMenuDo);
            }
        }
        return 1;
    }

    /**
     * 编辑用户菜单权限
     *
     * @param userId
     * @param reqVo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer updateUserMenu(int userId, UpdateUserMenuReqVo reqVo) {
        //删除用户原来的菜单权限
        systemUserMenuDao.deleteByUserId(userId);
        //新增用户菜单权限
        for (Integer menuId : reqVo.getMenuIds()) {
            SystemUserMenuDo systemUserMenuDo = new SystemUserMenuDo();
            systemUserMenuDo.setUserId(userId);
            systemUserMenuDo.setMenuId(menuId);
            systemUserMenuDo.setCreator(userId);
            systemUserMenuDo.setUpdater(userId);
            systemUserMenuDao.insertSelective(systemUserMenuDo);
        }
        return 1;
    }

    /**
     * 用户列表
     *
     * @param svcBean
     * @return
     */
    public List<UserListRespVo> getUserList(SystemUserSvcBean svcBean) {
        SystemRoleSvcBean roleSvcBean = new SystemRoleSvcBean();
        roleSvcBean.setName(svcBean.getRoleName());
        roleSvcBean.setDeleted(0);
        List<SystemRoleDo> systemRoleDos = systemRoleDao.queryAllByLimit(roleSvcBean);
        List<Integer> roleIds = CollectionUtils.convertList(systemRoleDos, SystemRoleDo::getId);
        Map<Integer, SystemRoleDo> roleMap = CollectionUtils.convertMap(systemRoleDos, SystemRoleDo::getId);
        svcBean.setRoleIds(roleIds);

        List<Integer> userIds = CollectionUtils.convertList(systemRoleDos, SystemRoleDo::getId);
        List<SystemUserMenuDo> systemUserMenuDos = systemUserMenuDao.queryByUserIds(userIds);
        Map<Integer, List<SystemUserMenuDo>> userMenuMap = CollectionUtils.convertMultiMap(systemUserMenuDos, SystemUserMenuDo::getUserId);

        List<SystemUserDo> systemUserDos = systemUserDao.queryAllByLimit(svcBean);
        List<UserListRespVo> respVos = new ArrayList<>();
        for (SystemUserDo systemUserDo : systemUserDos) {
            UserListRespVo respVo = new UserListRespVo();
            BeanUtil.copyProperties(systemUserDo, respVo);
            SystemRoleDo systemRoleDo = roleMap.get(systemUserDo.getRoleId());
            respVo.setRoleName(systemRoleDo.getName());
            respVo.setType(systemRoleDo.getType());
            List<SystemUserMenuDo> roleMenuDoList = userMenuMap.get(respVo.getId());
            List<Integer> menuIds = CollectionUtils.convertList(roleMenuDoList, SystemUserMenuDo::getMenuId);
            respVo.setMenuIds(menuIds);
            respVos.add(respVo);
        }
        return respVos;
    }

    /**
     * 编辑用户密码
     *
     * @param userId
     * @param reqVo
     * @return
     */
    public Integer updateUserPassword(int userId, UpdatePasswoedReqVo reqVo) {
        SystemUserDo systemUserDo = new SystemUserDo();
        systemUserDo.setId(reqVo.getUserId());
        systemUserDo.setPassword(passwordEncoder.encode(reqVo.getPassword()));
        systemUserDo.setUpdater(userId);
        return systemUserDao.updateSelective(systemUserDo);
    }
}
