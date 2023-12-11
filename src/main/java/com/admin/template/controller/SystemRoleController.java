package com.admin.template.controller;

import com.admin.template.bean.SystemRoleSvcBean;
import com.admin.template.dao.SystemRoleDao;
import com.admin.template.request.AddRoleReqVo;
import com.admin.template.request.RoleListReqVo;
import com.admin.template.request.UpdateRoleReqVo;
import com.admin.template.response.RoleListRespVo;
import com.admin.template.service.SystemRoleServiceImpl;
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
@Api(tags = "管理后台 - 角色")
@RequestMapping("/system/role")
@RestController
public class SystemRoleController {

    @Resource
    private SystemRoleServiceImpl systemRoleService;
    @Resource
    private SystemRoleDao systemRoleDao;

    @ApiOperation("角色列表")
    @PostMapping("get_role_list")
    public CommonResult<PageResult<RoleListRespVo>> getRoleList(@RequestBody RoleListReqVo reqVo) {
        SystemRoleSvcBean svcBean = new SystemRoleSvcBean();
        svcBean.setName(reqVo.getName());
        svcBean.setDeleted(0);
        if (reqVo.getPageNo() != null && reqVo.getPageSize() != null) {
            svcBean.setPageSize(reqVo.getPageSize());
            svcBean.setOffset((reqVo.getPageNo() - 1) * reqVo.getPageSize());
        }
        Long count = systemRoleDao.count(svcBean);
        if (count == 0) {
            return CommonResult.success(new PageResult<>(Collections.emptyList(), 0L));
        }
        List<RoleListRespVo> roleList = systemRoleService.getRoleList(svcBean);
        return CommonResult.success(new PageResult<>(roleList, count));
    }

    @ApiOperation("新增角色")
    @PostMapping("add_role")
    public CommonResult<Integer> addRole(@RequestBody @Valid AddRoleReqVo reqVo) {
        return CommonResult.success(systemRoleService.addRole(reqVo));
    }

    @ApiOperation("编辑角色")
    @PostMapping("update_role")
    public CommonResult<Integer> updateRole(@RequestBody @Valid UpdateRoleReqVo reqVo) {
        return CommonResult.success(systemRoleService.updateRole(reqVo));
    }

}
