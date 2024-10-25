package com.cl.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cl.utils.PageUtils;
import com.cl.utils.Query;


import com.cl.dao.XueshengkongbaiyemianDao;
import com.cl.entity.XueshengkongbaiyemianEntity;
import com.cl.service.XueshengkongbaiyemianService;
import com.cl.entity.view.XueshengkongbaiyemianView;

@Service("xueshengkongbaiyemianService")
public class XueshengkongbaiyemianServiceImpl extends ServiceImpl<XueshengkongbaiyemianDao, XueshengkongbaiyemianEntity> implements XueshengkongbaiyemianService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<XueshengkongbaiyemianEntity> page = baseMapper.selectPage(new Query<XueshengkongbaiyemianEntity>(params).getPage(),new QueryWrapper<XueshengkongbaiyemianEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<XueshengkongbaiyemianEntity> wrapper) {
        Page<XueshengkongbaiyemianEntity> page = baseMapper.selectPage(new Query<XueshengkongbaiyemianEntity>(params).getPage(),wrapper);
        Page<XueshengkongbaiyemianView> pageVo = (Page<XueshengkongbaiyemianView>)page.convert(XueshengkongbaiyemianView::new);
	    return new PageUtils(pageVo);
 	}
    
	@Override
	public List<XueshengkongbaiyemianView> selectListView(QueryWrapper<XueshengkongbaiyemianEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public XueshengkongbaiyemianView selectView(QueryWrapper<XueshengkongbaiyemianEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
