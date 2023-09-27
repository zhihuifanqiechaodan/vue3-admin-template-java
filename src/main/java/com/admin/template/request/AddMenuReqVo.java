package com.admin.template.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class AddMenuReqVo {

    @ApiModelProperty(value = "id")
    private Integer id;

    /**
     * 菜单、目录共用字段
     **/
    @ApiModelProperty(value = "菜单类型 0目录，1菜单，2按钮", notes = "菜单、目录共用", required = true)
    @NotNull(message = "type不能为空")
    private Integer type;

    @ApiModelProperty(value = "目录、菜单、按钮的名称", notes = "菜单、目录共用", required = true)
    @NotBlank(message = "title不能为空")
    private String title;

    @ApiModelProperty(value = "目录或菜单图标", notes = "菜单、目录共用", required = true)
    @NotBlank(message = "icon不能为空")
    private String icon;

    @ApiModelProperty(value = "0：显示 1：隐藏 （当设置为隐藏时，将不显示当前菜单或目录", notes = "菜单、目录共用", required = true)
    @NotNull(message = "hidden不能为空")
    private Boolean hidden;

    @ApiModelProperty(value = "0：true 1：false （当设置为false时，将不认证权限直接返回当前菜单或目录", notes = "菜单、目录共用", required = true)
    @NotNull(message = "auth不能为空")
    private Boolean auth;

    @ApiModelProperty(value = "按钮集合")
    public List<ButtonPermissions> buttonPermissions;


    /**
     * 目录字段
     **/
    @ApiModelProperty(value = "0：显示 1：隐藏 （当设置为显示时，当前目录下只有一个菜单，那么将显示目录", notes = "菜单")
    private Boolean show;

    @ApiModelProperty(value = "布局路由名称", notes = "菜单")
    private String layout;


    /**
     * 菜单字段
     **/
    @ApiModelProperty(value = "0：缓存 1：不缓存 （当设置为缓存时，将缓存当前页面的数据", notes = "目录")
    private Boolean cache;

    @ApiModelProperty(value = "0：显示 1：隐藏 （当设置为隐藏时，将不会出现在面包屑中【仅在经典布局中】", notes = "目录")
    private Boolean breadcrumb;

    @ApiModelProperty(value = "0：true 1：false （如果设置为true，它则会固定在tags-view中(默认 false,只在经典布局中展示)", notes = "目录")
    private Boolean affix;

    @ApiModelProperty(value = "如果设置了path，侧边栏会高亮显示你设置的路径", notes = "目录")
    private String activeMenu;

    @ApiModelProperty(value = "目录路径", notes = "目录")
    private String cataloguePath;

    @ApiModelProperty(value = "菜单路径", notes = "目录")
    private String path;

}


