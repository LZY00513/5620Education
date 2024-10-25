package com.cl.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cl.utils.PageUtils;
import com.cl.utils.Query;


import com.cl.dao.XueshengyuyuejiaoshiDao;
import com.cl.entity.XueshengyuyuejiaoshiEntity;
import com.cl.service.XueshengyuyuejiaoshiService;
import com.cl.entity.view.XueshengyuyuejiaoshiView;

@Service("xueshengyuyuejiaoshiService")
public class XueshengyuyuejiaoshiServiceImpl extends ServiceImpl<XueshengyuyuejiaoshiDao, XueshengyuyuejiaoshiEntity> implements XueshengyuyuejiaoshiService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<XueshengyuyuejiaoshiEntity> page = baseMapper.selectPage(new Query<XueshengyuyuejiaoshiEntity>(params).getPage(),new QueryWrapper<XueshengyuyuejiaoshiEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<XueshengyuyuejiaoshiEntity> wrapper) {
        Page<XueshengyuyuejiaoshiEntity> page = baseMapper.selectPage(new Query<XueshengyuyuejiaoshiEntity>(params).getPage(),wrapper);
        Page<XueshengyuyuejiaoshiView> pageVo = (Page<XueshengyuyuejiaoshiView>)page.convert(XueshengyuyuejiaoshiView::new);
	    return new PageUtils(pageVo);
 	}
    
	@Override
	public List<XueshengyuyuejiaoshiView> selectListView(QueryWrapper<XueshengyuyuejiaoshiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public XueshengyuyuejiaoshiView selectView(QueryWrapper<XueshengyuyuejiaoshiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
