package com.cl.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.TijiaozuoyeEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.TijiaozuoyeView;


/**
 * 提交作业
 *
 * @author 
 * @email 
 * @date 2024-10-24 16:43:16
 */
public interface TijiaozuoyeService extends IService<TijiaozuoyeEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<TijiaozuoyeView> selectListView(QueryWrapper<TijiaozuoyeEntity> wrapper);
   	
   	TijiaozuoyeView selectView(@Param("ew") QueryWrapper<TijiaozuoyeEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<TijiaozuoyeEntity> wrapper);
   	
   
}

