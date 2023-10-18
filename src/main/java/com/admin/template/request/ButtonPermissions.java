package com.admin.template.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @className: ButtonPermissions
 * @description:
 * @author: YangQian
 * @date: 2023/9/26 18:05
 */
@Data
public class ButtonPermissions {

    @ApiModelProperty(value = "唯一Id")
    private Integer Id;

    @ApiModelProperty(value = "按钮Id")
    private Integer value;

    @ApiModelProperty(value = "按钮名称")
    private String label;

}
