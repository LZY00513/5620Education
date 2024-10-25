package com.cl.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.XueshengyuyuejiaoshiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.XueshengyuyuejiaoshiView;


/**
 * 学生预约教师
 *
 * @author 
 * @email 
 * @date 2024-10-24 16:43:17
 */
public interface XueshengyuyuejiaoshiService extends IService<XueshengyuyuejiaoshiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<XueshengyuyuejiaoshiView> selectListView(QueryWrapper<XueshengyuyuejiaoshiEntity> wrapper);
   	
   	XueshengyuyuejiaoshiView selectView(@Param("ew") QueryWrapper<XueshengyuyuejiaoshiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<XueshengyuyuejiaoshiEntity> wrapper);
   	
   
}

