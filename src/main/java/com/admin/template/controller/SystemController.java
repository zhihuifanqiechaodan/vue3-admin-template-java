package com.admin.template.controller;

import cn.hutool.core.bean.BeanUtil;
import com.admin.template.domain.SystemUserDo;
import com.admin.template.request.LoginReqVo;
import com.admin.template.request.UserTokenReqVo;
import com.admin.template.response.LoginRespVo;
import com.admin.template.service.SystemServiceImpl;
import com.admin.template.utils.CommonResult;
import com.admin.template.utils.JWTUtils;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation("登录")
    @PostMapping("login")
    public CommonResult<LoginRespVo> login(@RequestBody @Valid LoginReqVo reqVo) {
        return CommonResult.success(systemService.login(reqVo));
    }

    @ApiOperation("获取token")
    @PostMapping("get_token")
    public CommonResult<String> getToken(@RequestBody @Valid UserTokenReqVo reqVo) {
        SystemUserDo systemUserDo = new SystemUserDo();
        BeanUtil.copyProperties(reqVo, systemUserDo);
        String token = JWTUtils.generateToken(systemUserDo);
        return CommonResult.success(token);
    }

    @ApiOperation("解析token")
    @GetMapping("validate_token")
    public CommonResult<DecodedJWT> validateToken(@RequestParam("token") String token) {
        return CommonResult.success(JWTUtils.validateToken(token));
    }

}
