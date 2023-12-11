package com.admin.template.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class UpdateUserMenuReqVo {

    @ApiModelProperty(value = "菜单ids")
    @NotEmpty(message = "权限至少选一个")
    private List<Integer> menuIds;

}

