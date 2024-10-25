package com.cl.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cl.utils.PageUtils;
import com.cl.utils.Query;


import com.cl.dao.JiazhangyuyuejiaoshiDao;
import com.cl.entity.JiazhangyuyuejiaoshiEntity;
import com.cl.service.JiazhangyuyuejiaoshiService;
import com.cl.entity.view.JiazhangyuyuejiaoshiView;

@Service("jiazhangyuyuejiaoshiService")
public class JiazhangyuyuejiaoshiServiceImpl extends ServiceImpl<JiazhangyuyuejiaoshiDao, JiazhangyuyuejiaoshiEntity> implements JiazhangyuyuejiaoshiService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<JiazhangyuyuejiaoshiEntity> page = baseMapper.selectPage(new Query<JiazhangyuyuejiaoshiEntity>(params).getPage(),new QueryWrapper<JiazhangyuyuejiaoshiEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<JiazhangyuyuejiaoshiEntity> wrapper) {
        Page<JiazhangyuyuejiaoshiEntity> page = baseMapper.selectPage(new Query<JiazhangyuyuejiaoshiEntity>(params).getPage(),wrapper);
        Page<JiazhangyuyuejiaoshiView> pageVo = (Page<JiazhangyuyuejiaoshiView>)page.convert(JiazhangyuyuejiaoshiView::new);
	    return new PageUtils(pageVo);
 	}
    
	@Override
	public List<JiazhangyuyuejiaoshiView> selectListView(QueryWrapper<JiazhangyuyuejiaoshiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public JiazhangyuyuejiaoshiView selectView(QueryWrapper<JiazhangyuyuejiaoshiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
