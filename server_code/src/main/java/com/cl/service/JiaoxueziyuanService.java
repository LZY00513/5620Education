package com.cl.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.JiaoxueziyuanEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.JiaoxueziyuanView;


/**
 * 教学资源
 *
 * @author 
 * @email 
 * @date 2024-10-24 16:43:16
 */
public interface JiaoxueziyuanService extends IService<JiaoxueziyuanEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<JiaoxueziyuanView> selectListView(QueryWrapper<JiaoxueziyuanEntity> wrapper);
   	
   	JiaoxueziyuanView selectView(@Param("ew") QueryWrapper<JiaoxueziyuanEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<JiaoxueziyuanEntity> wrapper);
   	
   
    List<Map<String, Object>> selectValue(Map<String, Object> params,QueryWrapper<JiaoxueziyuanEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,QueryWrapper<JiaoxueziyuanEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,QueryWrapper<JiaoxueziyuanEntity> wrapper);



}

