package com.cl.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.JiazhangyuyuejiaoshiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.JiazhangyuyuejiaoshiView;


/**
 * 家长预约教师
 *
 * @author 
 * @email 
 * @date 2024-10-24 16:43:17
 */
public interface JiazhangyuyuejiaoshiService extends IService<JiazhangyuyuejiaoshiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<JiazhangyuyuejiaoshiView> selectListView(QueryWrapper<JiazhangyuyuejiaoshiEntity> wrapper);
   	
   	JiazhangyuyuejiaoshiView selectView(@Param("ew") QueryWrapper<JiazhangyuyuejiaoshiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<JiazhangyuyuejiaoshiEntity> wrapper);
   	
   
}

