package com.admin.template.domain;

import lombok.Data;

import java.util.Date;

/**
 * 系统用户表(SystemUser)实体类
 *
 * @author makejava
 * @since 2023-09-25 11:23:42
 */
@Data
public class SystemUserDo {

    /**
     * 用户Id
     */
    private Integer id;
    /**
     * 用户名称
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 角色id
     */
    private Integer roleId;
    /**
     * 用户状态 0：正常 1：禁用
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

