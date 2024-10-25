package com.cl.dao;

import com.cl.entity.JiazhangEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.JiazhangView;


/**
 * 家长
 * 
 * @author 
 * @email 
 * @date 2024-10-24 16:43:16
 */
public interface JiazhangDao extends BaseMapper<JiazhangEntity> {
	
	List<JiazhangView> selectListView(@Param("ew") QueryWrapper<JiazhangEntity> wrapper);

	List<JiazhangView> selectListView(Page page,@Param("ew") QueryWrapper<JiazhangEntity> wrapper);
	
	JiazhangView selectView(@Param("ew") QueryWrapper<JiazhangEntity> wrapper);


}
