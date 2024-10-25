package com.cl.dao;

import com.cl.entity.ForumEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ForumView;


/**
 * 论坛交流
 * 
 * @author 
 * @email 
 * @date 2024-10-24 16:43:16
 */
public interface ForumDao extends BaseMapper<ForumEntity> {
	
	List<ForumView> selectListView(@Param("ew") QueryWrapper<ForumEntity> wrapper);

	List<ForumView> selectListView(Page page,@Param("ew") QueryWrapper<ForumEntity> wrapper);
	
	ForumView selectView(@Param("ew") QueryWrapper<ForumEntity> wrapper);


}
