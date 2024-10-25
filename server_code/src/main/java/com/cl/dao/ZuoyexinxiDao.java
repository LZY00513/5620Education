package com.cl.dao;

import com.cl.entity.ZuoyexinxiEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ZuoyexinxiView;


/**
 * 作业信息
 * 
 * @author 
 * @email 
 * @date 2024-10-24 16:43:16
 */
public interface ZuoyexinxiDao extends BaseMapper<ZuoyexinxiEntity> {
	
	List<ZuoyexinxiView> selectListView(@Param("ew") QueryWrapper<ZuoyexinxiEntity> wrapper);

	List<ZuoyexinxiView> selectListView(Page page,@Param("ew") QueryWrapper<ZuoyexinxiEntity> wrapper);
	
	ZuoyexinxiView selectView(@Param("ew") QueryWrapper<ZuoyexinxiEntity> wrapper);


}
