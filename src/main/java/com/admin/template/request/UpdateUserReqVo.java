package com.admin.template.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateUserReqVo {

    @ApiModelProperty(value = "更新的用户Id")
    @NotNull(message = "用户Id不能为空")
    private Integer userId;

    @ApiModelProperty(value = "角色Id")
    @NotNull(message = "角色不能为空")
    private Integer roleId;

    @ApiModelProperty(value = "用户状态 0：正常 1：禁用")
    @NotNull(message = "用户状态不能为空")
    private Integer status;

}

