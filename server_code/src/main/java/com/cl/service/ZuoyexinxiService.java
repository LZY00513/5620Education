package com.cl.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.ZuoyexinxiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ZuoyexinxiView;


/**
 * 作业信息
 *
 * @author 
 * @email 
 * @date 2024-10-24 16:43:16
 */
public interface ZuoyexinxiService extends IService<ZuoyexinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ZuoyexinxiView> selectListView(QueryWrapper<ZuoyexinxiEntity> wrapper);
   	
   	ZuoyexinxiView selectView(@Param("ew") QueryWrapper<ZuoyexinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<ZuoyexinxiEntity> wrapper);
   	
   
}

