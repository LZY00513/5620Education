package com.cl.dao;

import com.cl.entity.NewsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.NewsView;


/**
 * 公告信息
 * 
 * @author 
 * @email 
 * @date 2024-10-24 16:43:16
 */
public interface NewsDao extends BaseMapper<NewsEntity> {
	
	List<NewsView> selectListView(@Param("ew") QueryWrapper<NewsEntity> wrapper);

	List<NewsView> selectListView(Page page,@Param("ew") QueryWrapper<NewsEntity> wrapper);
	
	NewsView selectView(@Param("ew") QueryWrapper<NewsEntity> wrapper);


}
