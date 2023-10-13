package com.admin.template.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @className: MenuRqrVo
 * @description:
 * @author: YangQian
 * @date: 2023/10/13 16:23
 */
@Data
public class MenuReqVo {

    @ApiModelProperty(value = "排序数组")
    @NotEmpty(message = "排序数组不能为空")
    private List<MenuSortReqVo> sortList;

}
