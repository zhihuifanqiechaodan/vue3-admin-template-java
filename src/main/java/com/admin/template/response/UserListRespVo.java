package com.admin.template.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 系统用户表(SystemUser)出参
 *
 * @author makejava
 * @since 2023-09-25 11:23:43
 */
@Data
public class UserListRespVo {

    @ApiModelProperty(value = "用户id")
    private Integer id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "角色id")
    private Integer roleId;

    @ApiModelProperty(value = "角色名")
    private String roleName;

    @ApiModelProperty(value = "角色状态 0：正常 1：禁用")
    private Integer status;

}

