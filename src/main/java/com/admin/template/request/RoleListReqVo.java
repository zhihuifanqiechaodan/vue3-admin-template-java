package com.admin.template.request;

import com.admin.template.utils.PageParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RoleListReqVo extends PageParam {

    @ApiModelProperty(value = "角色名称")
    private String name;

}

