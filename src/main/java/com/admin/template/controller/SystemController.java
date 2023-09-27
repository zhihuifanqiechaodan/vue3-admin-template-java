package com.admin.template.controller;

import com.admin.template.request.LoginReqVo;
import com.admin.template.response.LoginRespVo;
import com.admin.template.service.SystemServiceImpl;
import com.admin.template.utils.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @className: SystemController
 * @description:
 * @author: YangQian
 * @date: 2023/9/22 14:41
 */
@Api(tags = "管理后台 - 系统")
@RequestMapping("/system/")
@RestController
public class SystemController {

    @Resource
    private SystemServiceImpl systemService;

    /**
     * 登录
     *
     * @param reqVo
     * @return
     */
    @ApiOperation("登录")
    @PostMapping("login")
    public CommonResult<LoginRespVo> login(@RequestBody @Valid LoginReqVo reqVo) {
        return CommonResult.success(systemService.login(reqVo));
    }

}
