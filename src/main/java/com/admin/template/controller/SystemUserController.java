package com.admin.template.controller;

import cn.hutool.core.bean.BeanUtil;
import com.admin.template.bean.SystemUserSvcBean;
import com.admin.template.dao.SystemUserDao;
import com.admin.template.request.*;
import com.admin.template.response.UserListRespVo;
import com.admin.template.service.SystemUserServiceImpl;
import com.admin.template.utils.CommonResult;
import com.admin.template.utils.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

/**
 * @className: SystemController
 * @description:
 * @author: YangQian
 * @date: 2023/9/22 14:41
 */
@Api(tags = "管理后台 - 用户")
@RequestMapping("/system/user")
@RestController
public class SystemUserController {

    @Resource
    private SystemUserServiceImpl systemUserService;
    @Resource
    private SystemUserDao systemUserDao;

    @ApiOperation("用户列表")
    @PostMapping("get_user_list")
    public CommonResult<PageResult<UserListRespVo>> getUserList(@RequestBody UserListReqVo reqVo) {
        SystemUserSvcBean svcBean = new SystemUserSvcBean();
        BeanUtil.copyProperties(reqVo, svcBean);
        Long count = systemUserDao.count(svcBean);
        if (count == 0) {
            return CommonResult.success(new PageResult<>(Collections.emptyList(), 0L));
        }
        List<UserListRespVo> roleList = systemUserService.getUserList(svcBean);
        return CommonResult.success(new PageResult<>(roleList, count));
    }

    @ApiOperation("新增用户")
    @PostMapping("add_user")
    public CommonResult<Integer> addUser(@RequestBody @Valid AddUserReqVo reqVo) {
        return CommonResult.success(systemUserService.addUser(reqVo));
    }

    @ApiOperation("编辑用户")
    @PostMapping("update_user")
    public CommonResult<Integer> updateUser(@RequestBody @Valid UpdateUserReqVo reqVo) {
        return CommonResult.success(systemUserService.updateUser(reqVo));
    }

    @ApiOperation("编辑用户菜单权限")
    @PostMapping("update_user_menu")
    public CommonResult<Integer> updateUserMenu(@RequestBody @Valid UpdateUserMenuReqVo reqVo) {
        return CommonResult.success(systemUserService.updateUserMenu(reqVo));
    }

    @ApiOperation("编辑用户密码")
    @PostMapping("update_user_password")
    public CommonResult<Integer> updateUserPassword(@RequestBody @Valid UpdatePasswoedReqVo reqVo) {
        return CommonResult.success(systemUserService.updateUserPassword(reqVo));
    }

}
