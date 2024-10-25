package com.cl.dao;

import com.cl.entity.TijiaozuoyeEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.TijiaozuoyeView;


/**
 * 提交作业
 * 
 * @author 
 * @email 
 * @date 2024-10-24 16:43:16
 */
public interface TijiaozuoyeDao extends BaseMapper<TijiaozuoyeEntity> {
	
	List<TijiaozuoyeView> selectListView(@Param("ew") QueryWrapper<TijiaozuoyeEntity> wrapper);

	List<TijiaozuoyeView> selectListView(Page page,@Param("ew") QueryWrapper<TijiaozuoyeEntity> wrapper);
	
	TijiaozuoyeView selectView(@Param("ew") QueryWrapper<TijiaozuoyeEntity> wrapper);


}
