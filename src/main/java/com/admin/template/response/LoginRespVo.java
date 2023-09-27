package com.admin.template.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginRespVo {

    @ApiModelProperty(value = "用户id")
    private Integer id;

    @ApiModelProperty(value = "用户名称")
    private String username;

    @ApiModelProperty(value = "token")
    private String token;
}

