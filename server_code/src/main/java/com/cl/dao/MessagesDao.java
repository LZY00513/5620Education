package com.cl.dao;

import com.cl.entity.MessagesEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.MessagesView;


/**
 * 留言板
 * 
 * @author 
 * @email 
 * @date 2024-10-24 16:43:16
 */
public interface MessagesDao extends BaseMapper<MessagesEntity> {
	
	List<MessagesView> selectListView(@Param("ew") QueryWrapper<MessagesEntity> wrapper);

	List<MessagesView> selectListView(Page page,@Param("ew") QueryWrapper<MessagesEntity> wrapper);
	
	MessagesView selectView(@Param("ew") QueryWrapper<MessagesEntity> wrapper);


}
