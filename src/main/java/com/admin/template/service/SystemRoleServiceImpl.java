package com.admin.template.service;

import cn.hutool.core.bean.BeanUtil;
import com.admin.template.bean.SystemRoleSvcBean;
import com.admin.template.dao.*;
import com.admin.template.domain.SystemRoleDo;
import com.admin.template.domain.SystemRoleMenuDo;
import com.admin.template.request.AddRoleReqVo;
import com.admin.template.request.UpdateRoleReqVo;
import com.admin.template.response.RoleListRespVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @className: SystemRoleServiceImpl
 * @description:
 * @author: YangQian
 * @date: 2023/9/22 14:42
 */
@Service
public class SystemRoleServiceImpl {

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

    /**
     * 角色列表
     *
     * @param reqVo
     * @return
     */
    public List<RoleListRespVo> getRoleList(SystemRoleSvcBean reqVo) {
        List<SystemRoleDo> systemRoleDos = systemRoleDao.queryAllByLimit(reqVo);
        List<RoleListRespVo> respVos = new ArrayList<>();
        for (SystemRoleDo systemRoleDo : systemRoleDos) {
            RoleListRespVo respVo = new RoleListRespVo();
            BeanUtil.copyProperties(systemRoleDo, respVo);
            respVos.add(respVo);
        }
        return respVos;
    }

    /**
     * 新增角色
     *
     * @param reqVo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer addRole(int userId, AddRoleReqVo reqVo) {
        //新增角色信息
        SystemRoleDo systemRoleDo = new SystemRoleDo();
        systemRoleDo.setName(reqVo.getName());
        systemRoleDo.setStatus(reqVo.getStatus());
        systemRoleDo.setCreator(userId);
        systemRoleDo.setUpdater(userId);
        systemRoleDao.insertSelective(systemRoleDo);
        //新增角色菜单信息
        for (Integer menuId : reqVo.getMenuIds()) {
            SystemRoleMenuDo roleMenuDo = new SystemRoleMenuDo();
            roleMenuDo.setRoleId(systemRoleDo.getId());
            roleMenuDo.setMenuId(menuId);
            roleMenuDo.setCreator(userId);
            roleMenuDo.setUpdater(userId);
            systemRoleMenuDao.insertSelective(roleMenuDo);
        }
        return systemRoleDo.getId();
    }

    /**
     * 编辑角色
     *
     * @param userId
     * @param reqVo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer updateRole(int userId, UpdateRoleReqVo reqVo) {
        //编辑角色信息
        SystemRoleDo systemRoleDo = new SystemRoleDo();
        systemRoleDo.setId(reqVo.getRoleId());
        systemRoleDo.setName(reqVo.getName());
        systemRoleDo.setStatus(reqVo.getStatus());
        systemRoleDo.setUpdater(userId);
        systemRoleDao.updateSelective(systemRoleDo);
        //编辑角色菜单信息
        systemRoleMenuDao.deleteByRoleId(reqVo.getRoleId());
        for (Integer menuId : reqVo.getMenuIds()) {
            SystemRoleMenuDo roleMenuDo = new SystemRoleMenuDo();
            roleMenuDo.setRoleId(reqVo.getRoleId());
            roleMenuDo.setMenuId(menuId);
            roleMenuDo.setCreator(userId);
            roleMenuDo.setUpdater(userId);
            systemRoleMenuDao.insertSelective(roleMenuDo);
        }
        return 1;
    }
}
