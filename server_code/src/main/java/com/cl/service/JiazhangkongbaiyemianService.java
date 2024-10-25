package com.cl.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.JiazhangkongbaiyemianEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.JiazhangkongbaiyemianView;


/**
 * 家长空白页面
 *
 * @author 
 * @email 
 * @date 2024-10-24 16:43:17
 */
public interface JiazhangkongbaiyemianService extends IService<JiazhangkongbaiyemianEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<JiazhangkongbaiyemianView> selectListView(QueryWrapper<JiazhangkongbaiyemianEntity> wrapper);
   	
   	JiazhangkongbaiyemianView selectView(@Param("ew") QueryWrapper<JiazhangkongbaiyemianEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<JiazhangkongbaiyemianEntity> wrapper);
   	
   
}

