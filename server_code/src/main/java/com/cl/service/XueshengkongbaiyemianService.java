package com.cl.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.XueshengkongbaiyemianEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.XueshengkongbaiyemianView;


/**
 * 学生空白页面
 *
 * @author 
 * @email 
 * @date 2024-10-24 16:43:17
 */
public interface XueshengkongbaiyemianService extends IService<XueshengkongbaiyemianEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<XueshengkongbaiyemianView> selectListView(QueryWrapper<XueshengkongbaiyemianEntity> wrapper);
   	
   	XueshengkongbaiyemianView selectView(@Param("ew") QueryWrapper<XueshengkongbaiyemianEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<XueshengkongbaiyemianEntity> wrapper);
   	
   
}

