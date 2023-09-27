package com.admin.template.service;

import cn.hutool.core.util.IdUtil;
import com.admin.template.bean.SystemRoleSvcBean;
import com.admin.template.bean.SystemUserSvcBean;
import com.admin.template.dao.SystemMenuDao;
import com.admin.template.dao.SystemRoleDao;
import com.admin.template.dao.SystemUserDao;
import com.admin.template.dao.SystemUserMenuDao;
import com.admin.template.domain.SystemRoleDo;
import com.admin.template.domain.SystemUserDo;
import com.admin.template.enums.RoleTypeEnum;
import com.admin.template.request.LoginReqVo;
import com.admin.template.response.LoginRespVo;
import com.admin.template.exception.ErrorCodeConstants;
import com.admin.template.exception.ServiceExceptionUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @className: SystemServiceImpl
 * @description:
 * @author: YangQian
 * @date: 2023/9/22 14:42
 */
@Service
public class SystemServiceImpl {

    @Resource
    private SystemUserDao systemUserDao;
    @Resource
    private SystemRoleDao systemRoleDao;
    @Resource
    private SystemMenuDao systemMenuDao;
    @Resource
    private SystemUserMenuDao systemUserMenuDao;

    /**
     * 登录
     *
     * @param reqVo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public LoginRespVo login(LoginReqVo reqVo) {
        SystemRoleSvcBean svcBean = new SystemRoleSvcBean();
        svcBean.setName(RoleTypeEnum.supers.getStatusName());
        svcBean.setType(RoleTypeEnum.supers.getType());
        svcBean.setDeleted(0);
        List<SystemRoleDo> systemRoleDos = systemRoleDao.queryAllByLimit(svcBean);
        //已存在系统管理员
        if (systemRoleDos != null && systemRoleDos.size() > 0) {
            return this.existSuperUser(reqVo);
        }
        //不存在系统管理员
        return this.notExistSuperUser(reqVo);
    }

    /**
     * 已存在系统管理员
     *
     * @param reqVo
     * @return
     */
    public LoginRespVo existSuperUser(LoginReqVo reqVo) {
        SystemUserSvcBean userSvcBean = new SystemUserSvcBean();
        userSvcBean.setUsername(reqVo.getUsername());
        userSvcBean.setDeleted(0);
        List<SystemUserDo> systemUserDos = systemUserDao.queryAllByLimit(userSvcBean);
        //用户不存在
        if (systemUserDos == null || systemUserDos.size() == 0) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.USER_NOT_EXIST);
        }
        //用户存在,密码不正确
        SystemUserDo systemUserDo = systemUserDos.get(0);
        if (systemUserDos != null && systemUserDos.size() > 0) {
            if (!systemUserDo.getPassword().equals(reqVo.getPassword())) {
                throw ServiceExceptionUtil.exception(ErrorCodeConstants.PASSWORD_ERROR);
            }
        }
        //登录成功
        LoginRespVo respVo = new LoginRespVo();
        respVo.setId(systemUserDo.getId());
        respVo.setUsername(systemUserDo.getUsername());
        //todo 从redis中获取token,没有创建token
        respVo.setToken(IdUtil.fastSimpleUUID());
        return respVo;
    }

    /**
     * 不存在系统管理员
     *
     * @param reqVo
     * @return
     */
    public LoginRespVo notExistSuperUser(LoginReqVo reqVo) {
        //创建超级管理员角色
        SystemRoleDo systemRoleDo = new SystemRoleDo();
        systemRoleDo.setName(RoleTypeEnum.supers.getStatusName());
        systemRoleDo.setType(RoleTypeEnum.supers.getType());
        systemRoleDao.insertSelective(systemRoleDo);
        Integer roleId = systemRoleDo.getId();
        //创建用户
        SystemUserDo systemUserDo = new SystemUserDo();
        systemUserDo.setUsername(reqVo.getUsername());
        systemUserDo.setPassword(reqVo.getPassword());
        systemUserDo.setRoleId(roleId);
        systemUserDao.insertSelective(systemUserDo);
        //返回用户信息
        LoginRespVo respVo = new LoginRespVo();
        respVo.setId(systemUserDo.getId());
        respVo.setUsername(systemUserDo.getUsername());
        //todo 创建token,存入redis
        respVo.setToken(IdUtil.fastSimpleUUID());
        return respVo;
    }

}
