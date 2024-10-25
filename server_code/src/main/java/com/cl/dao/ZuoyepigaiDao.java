package com.cl.dao;

import com.cl.entity.ZuoyepigaiEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ZuoyepigaiView;


/**
 * 作业批改
 * 
 * @author 
 * @email 
 * @date 2024-10-24 16:43:16
 */
public interface ZuoyepigaiDao extends BaseMapper<ZuoyepigaiEntity> {
	
	List<ZuoyepigaiView> selectListView(@Param("ew") QueryWrapper<ZuoyepigaiEntity> wrapper);

	List<ZuoyepigaiView> selectListView(Page page,@Param("ew") QueryWrapper<ZuoyepigaiEntity> wrapper);
	
	ZuoyepigaiView selectView(@Param("ew") QueryWrapper<ZuoyepigaiEntity> wrapper);


}
