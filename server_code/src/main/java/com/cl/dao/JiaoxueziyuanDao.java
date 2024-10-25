package com.cl.dao;

import com.cl.entity.JiaoxueziyuanEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.JiaoxueziyuanView;


/**
 * 教学资源
 * 
 * @author 
 * @email 
 * @date 2024-10-24 16:43:16
 */
public interface JiaoxueziyuanDao extends BaseMapper<JiaoxueziyuanEntity> {
	
	List<JiaoxueziyuanView> selectListView(@Param("ew") QueryWrapper<JiaoxueziyuanEntity> wrapper);

	List<JiaoxueziyuanView> selectListView(Page page,@Param("ew") QueryWrapper<JiaoxueziyuanEntity> wrapper);
	
	JiaoxueziyuanView selectView(@Param("ew") QueryWrapper<JiaoxueziyuanEntity> wrapper);


    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") QueryWrapper<JiaoxueziyuanEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") QueryWrapper<JiaoxueziyuanEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") QueryWrapper<JiaoxueziyuanEntity> wrapper);



}
