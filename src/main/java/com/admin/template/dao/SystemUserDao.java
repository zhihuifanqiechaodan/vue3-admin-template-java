package com.admin.template.dao;

import com.admin.template.bean.SystemUserSvcBean;
import com.admin.template.domain.SystemUserDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统用户表(SystemUserDo)表数据库访问层
 *
 * @author makejava
 * @since 2023-09-25 11:23:42
 */
@Mapper
public interface SystemUserDao {

    SystemUserDo queryById(@Param("id") Integer id);

    List<SystemUserDo> queryByIds(@Param("ids") List<Long> ids);

    List<SystemUserDo> queryAllByLimit(SystemUserSvcBean systemUserSvcBean);

    long count(SystemUserSvcBean systemUserSvcBean);

    int insertSelective(SystemUserDo systemUserDo);

    int updateSelective(SystemUserDo systemUserDo);

    int deleteById(@Param("id") Integer id);

}

