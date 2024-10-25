package com.cl.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.JiaoshikongbaiyemianEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.JiaoshikongbaiyemianView;


/**
 * 教师空白页面
 *
 * @author 
 * @email 
 * @date 2024-10-24 16:43:17
 */
public interface JiaoshikongbaiyemianService extends IService<JiaoshikongbaiyemianEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<JiaoshikongbaiyemianView> selectListView(QueryWrapper<JiaoshikongbaiyemianEntity> wrapper);
   	
   	JiaoshikongbaiyemianView selectView(@Param("ew") QueryWrapper<JiaoshikongbaiyemianEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<JiaoshikongbaiyemianEntity> wrapper);
   	
   
}

