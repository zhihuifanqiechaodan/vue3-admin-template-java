package com.admin.template.dao;

import com.admin.template.bean.SystemRoleMenuSvcBean;
import com.admin.template.domain.SystemRoleMenuDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统角色菜单关系表(SystemRoleMenuDo)表数据库访问层
 *
 * @author makejava
 * @since 2023-09-25 11:23:42
 */
@Mapper
public interface SystemRoleMenuDao {

    List<SystemRoleMenuDo> queryByRoleId(@Param("roleId") Integer roleId);

    List<SystemRoleMenuDo> queryByRoleIds(@Param("roleIds") List<Integer> roleIds);

    List<SystemRoleMenuDo> queryAllByLimit(SystemRoleMenuSvcBean systemRoleMenuSvcBean);

    long count(SystemRoleMenuSvcBean systemRoleMenuSvcBean);

    int insertSelective(SystemRoleMenuDo systemRoleMenuDo);

    int updateSelective(SystemRoleMenuDo systemRoleMenuDo);

    int deleteByRoleId(@Param("roleId") Integer roleId);

}

