package com.cl.dao;

import com.cl.entity.JiazhangyuyuejiaoshiEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.JiazhangyuyuejiaoshiView;


/**
 * 家长预约教师
 * 
 * @author 
 * @email 
 * @date 2024-10-24 16:43:17
 */
public interface JiazhangyuyuejiaoshiDao extends BaseMapper<JiazhangyuyuejiaoshiEntity> {
	
	List<JiazhangyuyuejiaoshiView> selectListView(@Param("ew") QueryWrapper<JiazhangyuyuejiaoshiEntity> wrapper);

	List<JiazhangyuyuejiaoshiView> selectListView(Page page,@Param("ew") QueryWrapper<JiazhangyuyuejiaoshiEntity> wrapper);
	
	JiazhangyuyuejiaoshiView selectView(@Param("ew") QueryWrapper<JiazhangyuyuejiaoshiEntity> wrapper);


}
