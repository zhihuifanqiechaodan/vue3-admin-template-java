package com.admin.template.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdatePasswoedReqVo {

    @ApiModelProperty(value = "更新的用户Id")
    @NotNull(message = "用户Id不能为空")
    private Integer userId;

    @ApiModelProperty(value = "用户新的密码")
    @NotBlank(message = "用户密码不能为空")
    private String password;

}

