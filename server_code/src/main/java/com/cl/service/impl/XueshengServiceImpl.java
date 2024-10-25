package com.cl.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cl.utils.PageUtils;
import com.cl.utils.Query;


import com.cl.dao.XueshengDao;
import com.cl.entity.XueshengEntity;
import com.cl.service.XueshengService;
import com.cl.entity.view.XueshengView;

@Service("xueshengService")
public class XueshengServiceImpl extends ServiceImpl<XueshengDao, XueshengEntity> implements XueshengService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<XueshengEntity> page = baseMapper.selectPage(new Query<XueshengEntity>(params).getPage(),new QueryWrapper<XueshengEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<XueshengEntity> wrapper) {
        Page<XueshengEntity> page = baseMapper.selectPage(new Query<XueshengEntity>(params).getPage(),wrapper);
        Page<XueshengView> pageVo = (Page<XueshengView>)page.convert(XueshengView::new);
	    return new PageUtils(pageVo);
 	}
    
	@Override
	public List<XueshengView> selectListView(QueryWrapper<XueshengEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public XueshengView selectView(QueryWrapper<XueshengEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, QueryWrapper<XueshengEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, QueryWrapper<XueshengEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, QueryWrapper<XueshengEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }




}
