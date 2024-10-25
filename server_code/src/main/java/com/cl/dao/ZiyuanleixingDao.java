package com.cl.dao;

import com.cl.entity.ZiyuanleixingEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ZiyuanleixingView;


/**
 * 资源类型
 * 
 * @author 
 * @email 
 * @date 2024-10-24 16:43:16
 */
public interface ZiyuanleixingDao extends BaseMapper<ZiyuanleixingEntity> {
	
	List<ZiyuanleixingView> selectListView(@Param("ew") QueryWrapper<ZiyuanleixingEntity> wrapper);

	List<ZiyuanleixingView> selectListView(Page page,@Param("ew") QueryWrapper<ZiyuanleixingEntity> wrapper);
	
	ZiyuanleixingView selectView(@Param("ew") QueryWrapper<ZiyuanleixingEntity> wrapper);


}
