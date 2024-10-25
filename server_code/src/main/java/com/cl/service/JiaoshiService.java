package com.cl.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.JiaoshiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.JiaoshiView;


/**
 * 教师
 *
 * @author 
 * @email 
 * @date 2024-10-24 16:43:16
 */
public interface JiaoshiService extends IService<JiaoshiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<JiaoshiView> selectListView(QueryWrapper<JiaoshiEntity> wrapper);
   	
   	JiaoshiView selectView(@Param("ew") QueryWrapper<JiaoshiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<JiaoshiEntity> wrapper);
   	
   
}

