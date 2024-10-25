package com.cl.dao;

import com.cl.entity.UsersEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.UsersView;


/**
 * 管理员
 * 
 * @author 
 * @email 
 * @date 2024-10-24 16:43:16
 */
public interface UsersDao extends BaseMapper<UsersEntity> {
	
	List<UsersView> selectListView(@Param("ew") QueryWrapper<UsersEntity> wrapper);

	List<UsersView> selectListView(Page page,@Param("ew") QueryWrapper<UsersEntity> wrapper);
	
	UsersView selectView(@Param("ew") QueryWrapper<UsersEntity> wrapper);


}
