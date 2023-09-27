package com.admin.template.utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 分页模版
 */
@Data
public class PageParam implements Serializable {

    private static final Integer PAGE_NO = 1;
    private static final Integer PAGE_SIZE = 10;

    @ApiModelProperty(value = "页码，从 1 开始", required = false, example = "1")
    private Integer pageNo = PAGE_NO;

    @ApiModelProperty(value = "每页条数，最大值为 100", required = false, example = "10")
    private Integer pageSize = PAGE_SIZE;

    @ApiModelProperty(value = "mysql开始位置", example = "0", hidden = true)
    private Integer offset = (pageNo - 1) * pageSize;

    public Integer getOffset() {
        return (this.pageNo - 1) * this.pageSize;
    }
}
