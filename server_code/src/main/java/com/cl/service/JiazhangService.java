package com.cl.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.JiazhangEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.JiazhangView;


/**
 * 家长
 *
 * @author 
 * @email 
 * @date 2024-10-24 16:43:16
 */
public interface JiazhangService extends IService<JiazhangEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<JiazhangView> selectListView(QueryWrapper<JiazhangEntity> wrapper);
   	
   	JiazhangView selectView(@Param("ew") QueryWrapper<JiazhangEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<JiazhangEntity> wrapper);
   	
   
}

