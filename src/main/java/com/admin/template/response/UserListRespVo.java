package com.admin.template.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

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

    @ApiModelProperty(value = "用户关联菜单ids")
    private List<Integer> menuIds;

    @ApiModelProperty(value = "用户类型 0普通用户，1超级管理员")
    private Integer type;

}

