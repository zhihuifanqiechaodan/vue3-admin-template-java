package com.admin.template.request;

import com.admin.template.utils.PageParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserListReqVo extends PageParam {

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "角色id")
    private Integer roleId;

    @ApiModelProperty(value = "角色名")
    private String roleName;

    @ApiModelProperty(value = "角色状态 0：正常 1：禁用")
    private Integer status;
}
