package com.cl.dao;

import com.cl.entity.JiaoshiEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.JiaoshiView;


/**
 * 教师
 * 
 * @author 
 * @email 
 * @date 2024-10-24 16:43:16
 */
public interface JiaoshiDao extends BaseMapper<JiaoshiEntity> {
	
	List<JiaoshiView> selectListView(@Param("ew") QueryWrapper<JiaoshiEntity> wrapper);

	List<JiaoshiView> selectListView(Page page,@Param("ew") QueryWrapper<JiaoshiEntity> wrapper);
	
	JiaoshiView selectView(@Param("ew") QueryWrapper<JiaoshiEntity> wrapper);


}
