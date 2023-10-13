package com.admin.template.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserTokenReqVo {

    @ApiModelProperty(value = "用户Id")
    @NotNull(message = "用户Id不能为空")
    private Integer id;

    @ApiModelProperty(value = "用户名称")
    @NotNull(message = "用户名称不能为空")
    private String username;

    @ApiModelProperty(value = "用户密码")
    @NotNull(message = "用户密码不能为空")
    private String password;

}

