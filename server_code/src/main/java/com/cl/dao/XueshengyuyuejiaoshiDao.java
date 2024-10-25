package com.cl.dao;

import com.cl.entity.XueshengyuyuejiaoshiEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.XueshengyuyuejiaoshiView;


/**
 * 学生预约教师
 * 
 * @author 
 * @email 
 * @date 2024-10-24 16:43:17
 */
public interface XueshengyuyuejiaoshiDao extends BaseMapper<XueshengyuyuejiaoshiEntity> {
	
	List<XueshengyuyuejiaoshiView> selectListView(@Param("ew") QueryWrapper<XueshengyuyuejiaoshiEntity> wrapper);

	List<XueshengyuyuejiaoshiView> selectListView(Page page,@Param("ew") QueryWrapper<XueshengyuyuejiaoshiEntity> wrapper);
	
	XueshengyuyuejiaoshiView selectView(@Param("ew") QueryWrapper<XueshengyuyuejiaoshiEntity> wrapper);


}
