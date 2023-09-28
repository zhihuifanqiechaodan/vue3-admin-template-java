package com.admin.template.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class MenuSortReqVo {

    @ApiModelProperty(value = "菜单ids", notes = "按拖拽后的菜单排列顺序 把菜单的id传过来")
    @NotEmpty(message = "菜单ids不能为空")
    private List<Integer> menuIds;

}

