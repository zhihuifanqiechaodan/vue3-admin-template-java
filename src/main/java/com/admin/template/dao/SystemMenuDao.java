package com.admin.template.dao;

import com.admin.template.bean.SystemMenuSvcBean;
import com.admin.template.domain.SystemMenuDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统菜单表(SystemMenuDo)表数据库访问层
 *
 * @author makejava
 * @since 2023-09-25 11:23:38
 */
@Mapper
public interface SystemMenuDao {

    SystemMenuDo queryById(@Param("id") Integer id);

    List<SystemMenuDo> queryByIds(@Param("ids") List<Integer> ids);

    List<SystemMenuDo> queryAllByLimit(SystemMenuSvcBean systemMenuSvcBean);

    long count(SystemMenuSvcBean systemMenuSvcBean);

    int insertSelective(SystemMenuDo systemMenuDo);

    int updateSelective(SystemMenuDo systemMenuDo);

    int deleteByParentId(@Param("parentId") Integer parentId);

    int updateButtonTitle(SystemMenuDo systemMenuDo);

}

