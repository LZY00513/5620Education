package com.cl.dao;

import com.cl.entity.ConfigEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ConfigView;


/**
 * 轮播图
 * 
 * @author 
 * @email 
 * @date 2024-10-24 16:43:15
 */
public interface ConfigDao extends BaseMapper<ConfigEntity> {
	
	List<ConfigView> selectListView(@Param("ew") QueryWrapper<ConfigEntity> wrapper);

	List<ConfigView> selectListView(Page page,@Param("ew") QueryWrapper<ConfigEntity> wrapper);
	
	ConfigView selectView(@Param("ew") QueryWrapper<ConfigEntity> wrapper);


}
