package com.admin.template.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class AddRoleReqVo {

    @ApiModelProperty(value = "角色名称")
    @NotBlank(message = "角色名称不能为空")
    private String name;

    @ApiModelProperty(value = "菜单ids")
    @NotEmpty(message = "权限至少选一个")
    private List<Integer> menuIds;

    @ApiModelProperty(value = "角色状态 0：启用 1：禁用")
    @NotNull(message = "角色状态不能为空")
    private Integer status;
}

