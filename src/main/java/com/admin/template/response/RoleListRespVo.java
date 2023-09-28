package com.admin.template.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class RoleListRespVo {

    @ApiModelProperty(value = "角色id")
    private Integer id;

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "角色类型 0普通角色，1超级管理员")
    private Integer type;

    @ApiModelProperty(value = "角色状态 0：正常 1：禁用")
    private Integer status;

    @ApiModelProperty(value = "角色关联菜单ids")
    private List<Integer> menuIds;

}

