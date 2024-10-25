package com.cl.dao;

import com.cl.entity.JiaoshikongbaiyemianEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.JiaoshikongbaiyemianView;


/**
 * 教师空白页面
 * 
 * @author 
 * @email 
 * @date 2024-10-24 16:43:17
 */
public interface JiaoshikongbaiyemianDao extends BaseMapper<JiaoshikongbaiyemianEntity> {
	
	List<JiaoshikongbaiyemianView> selectListView(@Param("ew") QueryWrapper<JiaoshikongbaiyemianEntity> wrapper);

	List<JiaoshikongbaiyemianView> selectListView(Page page,@Param("ew") QueryWrapper<JiaoshikongbaiyemianEntity> wrapper);
	
	JiaoshikongbaiyemianView selectView(@Param("ew") QueryWrapper<JiaoshikongbaiyemianEntity> wrapper);


}
