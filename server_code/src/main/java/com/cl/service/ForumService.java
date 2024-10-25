package com.cl.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.ForumEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ForumView;


/**
 * 论坛交流
 *
 * @author 
 * @email 
 * @date 2024-10-24 16:43:16
 */
public interface ForumService extends IService<ForumEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ForumView> selectListView(QueryWrapper<ForumEntity> wrapper);
   	
   	ForumView selectView(@Param("ew") QueryWrapper<ForumEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<ForumEntity> wrapper);
   	
   
}

