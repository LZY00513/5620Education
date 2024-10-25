package com.cl.dao;

import com.cl.entity.XueshengkongbaiyemianEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.XueshengkongbaiyemianView;


/**
 * 学生空白页面
 * 
 * @author 
 * @email 
 * @date 2024-10-24 16:43:17
 */
public interface XueshengkongbaiyemianDao extends BaseMapper<XueshengkongbaiyemianEntity> {
	
	List<XueshengkongbaiyemianView> selectListView(@Param("ew") QueryWrapper<XueshengkongbaiyemianEntity> wrapper);

	List<XueshengkongbaiyemianView> selectListView(Page page,@Param("ew") QueryWrapper<XueshengkongbaiyemianEntity> wrapper);
	
	XueshengkongbaiyemianView selectView(@Param("ew") QueryWrapper<XueshengkongbaiyemianEntity> wrapper);


}
