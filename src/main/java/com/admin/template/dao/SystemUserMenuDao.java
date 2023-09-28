package com.admin.template.dao;

import com.admin.template.bean.SystemUserMenuSvcBean;
import com.admin.template.domain.SystemUserMenuDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统角色菜单关系表(SystemUserMenuDo)表数据库访问层
 *
 * @author makejava
 * @since 2023-09-25 14:50:44
 */
@Mapper
public interface SystemUserMenuDao {

    SystemUserMenuDo queryById(@Param("id") Integer id);

    List<SystemUserMenuDo> queryByUserIds(@Param("userIds") List<Integer> userIds);

    List<SystemUserMenuDo> queryAllByLimit(SystemUserMenuSvcBean systemUserMenuSvcBean);

    long count(SystemUserMenuSvcBean systemUserMenuSvcBean);

    int insertSelective(SystemUserMenuDo systemUserMenuDo);

    int updateSelective(SystemUserMenuDo systemUserMenuDo);

    int deleteByUserId(@Param("userId") Integer userId);

}

