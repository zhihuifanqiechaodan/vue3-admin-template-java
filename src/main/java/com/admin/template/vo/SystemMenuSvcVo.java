package com.admin.template.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 系统菜单表(SystemMenu)出参
 *
 * @author makejava
 * @since 2023-09-25 11:23:41
 */
@Data
public class SystemMenuSvcVo {

    @ApiModelProperty(value = "菜单id")
    private Integer id;

    @ApiModelProperty(value = "父级id")
    private Integer parentId;

    @ApiModelProperty(value = "按钮id")
    private Integer buttonId;

    @ApiModelProperty(value = "菜单类型 0目录，1菜单，2按钮")
    private Integer type;

    @ApiModelProperty(value = "目录 ｜ 菜单 | 按钮名称")
    private String title;

    @ApiModelProperty(value = "目录或菜单图标")
    private String icon;

    @ApiModelProperty(value = "0：显示 1：隐藏 （当设置为隐藏时，将不显示当前菜单或目录")
    private Boolean hidden;

    @ApiModelProperty(value = "0：显示 1：隐藏 （当设置为显示时，当前目录下只有一个菜单，那么将显示目录")
    private Boolean show;

    @ApiModelProperty(value = "0：缓存 1：不缓存 （当设置为缓存时，将缓存当前页面的数据")
    private Boolean cache;

    @ApiModelProperty(value = "0：显示 1：隐藏 （当设置为隐藏时，将不会出现在面包屑中【仅在经典布局中】")
    private Boolean breadcrumb;

    @ApiModelProperty(value = "0：true 1：false （如果设置为true，它则会固定在tags-view中(默认 false,只在经典布局中展示)")
    private Boolean affix;

    @ApiModelProperty(value = "0：true 1：false （当设置为false时，将不认证权限直接返回当前菜单或目录")
    private Boolean auth;

    @ApiModelProperty(value = "如果设置了path，侧边栏会高亮显示你设置的路径")
    private String activeMenu;

    @ApiModelProperty(value = "布局路由名称")
    private String layout;

    @ApiModelProperty(value = "目录路径")
    private String cataloguePath;

    @ApiModelProperty(value = "菜单路径")
    private String path;

    @ApiModelProperty(value = "排序")
    private Integer sort;

}

