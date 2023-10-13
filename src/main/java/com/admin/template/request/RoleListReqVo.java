package com.admin.template.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RoleListReqVo {

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "页码，从 1 开始")
    private Integer pageNo;

    @ApiModelProperty(value = "每页条数，最大值为 100")
    private Integer pageSize;

}

