package com.cl.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.XueshengEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.XueshengView;


/**
 * 学生
 *
 * @author 
 * @email 
 * @date 2024-10-24 16:43:16
 */
public interface XueshengService extends IService<XueshengEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<XueshengView> selectListView(QueryWrapper<XueshengEntity> wrapper);
   	
   	XueshengView selectView(@Param("ew") QueryWrapper<XueshengEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<XueshengEntity> wrapper);
   	
   
    List<Map<String, Object>> selectValue(Map<String, Object> params,QueryWrapper<XueshengEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,QueryWrapper<XueshengEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,QueryWrapper<XueshengEntity> wrapper);



}

