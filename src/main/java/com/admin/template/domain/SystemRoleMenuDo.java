package com.admin.template.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 系统角色菜单关系表(SystemRoleMenu)实体类
 *
 * @author makejava
 * @since 2023-09-25 11:23:42
 */
@Data
public class SystemRoleMenuDo {

    private Integer id;
    /**
     * 角色id
     */
    private Integer roleId;
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

    /**
     * 菜单ids
     */
    private List<Integer> menuIds;

}

