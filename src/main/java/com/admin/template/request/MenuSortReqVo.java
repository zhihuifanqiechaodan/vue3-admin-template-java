package com.admin.template.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MenuSortReqVo {

    @ApiModelProperty(value = "菜单id")
    @NotNull(message = "菜单id不能为空")
    private Integer menuId;

    @ApiModelProperty(value = "目录id")
    private Integer parentId;

    @ApiModelProperty(value = "排序")
    @NotNull(message = "排序不能为空")
    private Integer sort;

}

