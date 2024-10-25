package com.cl.dao;

import com.cl.entity.JiazhangkongbaiyemianEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.JiazhangkongbaiyemianView;


/**
 * 家长空白页面
 * 
 * @author 
 * @email 
 * @date 2024-10-24 16:43:17
 */
public interface JiazhangkongbaiyemianDao extends BaseMapper<JiazhangkongbaiyemianEntity> {
	
	List<JiazhangkongbaiyemianView> selectListView(@Param("ew") QueryWrapper<JiazhangkongbaiyemianEntity> wrapper);

	List<JiazhangkongbaiyemianView> selectListView(Page page,@Param("ew") QueryWrapper<JiazhangkongbaiyemianEntity> wrapper);
	
	JiazhangkongbaiyemianView selectView(@Param("ew") QueryWrapper<JiazhangkongbaiyemianEntity> wrapper);


}
