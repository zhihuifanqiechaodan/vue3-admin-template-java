package com.admin.template.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddUserReqVo {

    @ApiModelProperty(value = "用户名称")
    @NotNull(message = "用户名称不能为空")
    private String username;

    @ApiModelProperty(value = "用户密码")
    @NotNull(message = "用户密码不能为空")
    private String password;

    @ApiModelProperty(value = "角色Id")
    @NotNull(message = "角色不能为空")
    private Integer roleId;

}

