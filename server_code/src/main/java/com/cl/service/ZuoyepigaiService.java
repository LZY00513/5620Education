package com.cl.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.ZuoyepigaiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ZuoyepigaiView;


/**
 * 作业批改
 *
 * @author 
 * @email 
 * @date 2024-10-24 16:43:16
 */
public interface ZuoyepigaiService extends IService<ZuoyepigaiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ZuoyepigaiView> selectListView(QueryWrapper<ZuoyepigaiEntity> wrapper);
   	
   	ZuoyepigaiView selectView(@Param("ew") QueryWrapper<ZuoyepigaiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<ZuoyepigaiEntity> wrapper);
   	
   
}

