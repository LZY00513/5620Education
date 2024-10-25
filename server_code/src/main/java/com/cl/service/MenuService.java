package com.cl.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.MenuEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.MenuView;


/**
 * 菜单
 *
 * @author 
 * @email 
 * @date 2024-10-24 16:43:16
 */
public interface MenuService extends IService<MenuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<MenuView> selectListView(QueryWrapper<MenuEntity> wrapper);
   	
   	MenuView selectView(@Param("ew") QueryWrapper<MenuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<MenuEntity> wrapper);
   	
   
}

