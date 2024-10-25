package com.cl.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cl.utils.PageUtils;
import com.cl.utils.Query;


import com.cl.dao.JiaoshiDao;
import com.cl.entity.JiaoshiEntity;
import com.cl.service.JiaoshiService;
import com.cl.entity.view.JiaoshiView;

@Service("jiaoshiService")
public class JiaoshiServiceImpl extends ServiceImpl<JiaoshiDao, JiaoshiEntity> implements JiaoshiService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<JiaoshiEntity> page = baseMapper.selectPage(new Query<JiaoshiEntity>(params).getPage(),new QueryWrapper<JiaoshiEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<JiaoshiEntity> wrapper) {
        Page<JiaoshiEntity> page = baseMapper.selectPage(new Query<JiaoshiEntity>(params).getPage(),wrapper);
        Page<JiaoshiView> pageVo = (Page<JiaoshiView>)page.convert(JiaoshiView::new);
	    return new PageUtils(pageVo);
 	}
    
	@Override
	public List<JiaoshiView> selectListView(QueryWrapper<JiaoshiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public JiaoshiView selectView(QueryWrapper<JiaoshiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
