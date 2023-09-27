package com.admin.template.vo;

import lombok.Data;

import java.util.Date;

/**
 * 系统角色菜单关系表(SystemUserMenu)出参
 *
 * @author makejava
 * @since 2023-09-25 14:50:49
 */
@Data
public class SystemUserMenuSvcVo {

    private Integer id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 菜单id
     */
    private Integer menuId;
    /**
     * 角色状态 0：正常 1：禁用
     */
    private Integer status;
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

