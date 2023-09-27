package com.admin.template.domain;

import lombok.Data;

import java.util.Date;

/**
 * 系统菜单表(SystemMenu)实体类
 *
 * @author makejava
 * @since 2023-09-25 11:23:39
 */
@Data
public class SystemMenuDo {

    private Integer id;
    /**
     * 目录id
     */
    private Integer parentId;
    /**
     * 按钮id
     */
    private Integer buttonId;
    /**
     * 菜单类型 0目录，1菜单，2按钮
     */
    private Integer type;
    /**
     * 目录 ｜ 菜单 | 按钮名称
     */
    private String title;
    /**
     * 目录或菜单图标
     */
    private String icon;
    /**
     * 0：显示 1：隐藏 （当设置为隐藏时，将不显示当前菜单或目录
     */
    private Integer hidden;
    /**
     * 0：显示 1：隐藏 （当设置为显示时，当前目录下只有一个菜单，那么将显示目录
     */
    private Integer show;
    /**
     * 0：缓存 1：不缓存 （当设置为缓存时，将缓存当前页面的数据
     */
    private Integer cache;
    /**
     * 0：显示 1：隐藏 （当设置为隐藏时，将不会出现在面包屑中【仅在经典布局中】
     */
    private Integer breadcrumb;
    /**
     * 0：true 1：false （如果设置为true，它则会固定在tags-view中(默认 false,只在经典布局中展示)
     */
    private Integer affix;
    /**
     * 0：true 1：false （当设置为false时，将不认证权限直接返回当前菜单或目录
     */
    private Integer auth;
    /**
     * 如果设置了path，侧边栏会高亮显示你设置的路径
     */
    private String activeMenu;
    /**
     * 布局路由名称
     */
    private String layout;
    /**
     * 目录路径
     */
    private String cataloguePath;
    /**
     * 菜单路径
     */
    private String path;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 删除状态 0：正常 1：禁用
     */
    private Integer deleted;
    /**
     * 创建用户id
     */
    private Integer creator;
    /**
     * 更新用户id
     */
    private Integer updater;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

}

