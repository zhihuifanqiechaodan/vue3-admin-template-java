package com.admin.template.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AddUserReqVo {

    @ApiModelProperty(value = "用户名称")
    @NotBlank(message = "用户名称不能为空")
    private String username;

    @ApiModelProperty(value = "用户密码")
    @NotBlank(message = "用户密码不能为空")
    private String password;

    @ApiModelProperty(value = "角色Id")
    @NotNull(message = "角色不能为空")
    private Integer roleId;

    @ApiModelProperty(value = "角色状态 0：正常 1：禁用")
    @NotNull(message = "角色状态不能为空")
    private Integer status;

}

