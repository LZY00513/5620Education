package com.cl.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.ZiyuanleixingEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ZiyuanleixingView;


/**
 * 资源类型
 *
 * @author 
 * @email 
 * @date 2024-10-24 16:43:16
 */
public interface ZiyuanleixingService extends IService<ZiyuanleixingEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ZiyuanleixingView> selectListView(QueryWrapper<ZiyuanleixingEntity> wrapper);
   	
   	ZiyuanleixingView selectView(@Param("ew") QueryWrapper<ZiyuanleixingEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<ZiyuanleixingEntity> wrapper);
   	
   
}

