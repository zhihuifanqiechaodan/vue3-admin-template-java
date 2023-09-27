package com.admin.template.domain;

import lombok.Data;

import java.util.Date;

/**
 * 系统角色表(SystemRole)实体类
 *
 * @author makejava
 * @since 2023-09-25 11:23:42
 */
@Data
public class SystemRoleDo {

    private Integer id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色类型 0普通角色，1超级管理员
     */
    private Integer type;
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

