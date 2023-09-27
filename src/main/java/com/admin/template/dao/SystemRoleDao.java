package com.admin.template.dao;

import com.admin.template.bean.SystemRoleSvcBean;
import com.admin.template.domain.SystemRoleDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统角色表(SystemRoleDo)表数据库访问层
 *
 * @author makejava
 * @since 2023-09-25 11:23:42
 */
@Mapper
public interface SystemRoleDao {

    SystemRoleDo queryById(@Param("id") Integer id);

    List<SystemRoleDo> queryByIds(@Param("ids") List<Long> ids);

    List<SystemRoleDo> queryAllByLimit(SystemRoleSvcBean systemRoleSvcBean);

    long count(SystemRoleSvcBean systemRoleSvcBean);

    int insertSelective(SystemRoleDo systemRoleDo);

    int updateSelective(SystemRoleDo systemRoleDo);

    int deleteById(@Param("id") Integer id);

}

