package com.cl.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cl.utils.PageUtils;
import com.cl.utils.Query;


import com.cl.dao.JiazhangkongbaiyemianDao;
import com.cl.entity.JiazhangkongbaiyemianEntity;
import com.cl.service.JiazhangkongbaiyemianService;
import com.cl.entity.view.JiazhangkongbaiyemianView;

@Service("jiazhangkongbaiyemianService")
public class JiazhangkongbaiyemianServiceImpl extends ServiceImpl<JiazhangkongbaiyemianDao, JiazhangkongbaiyemianEntity> implements JiazhangkongbaiyemianService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<JiazhangkongbaiyemianEntity> page = baseMapper.selectPage(new Query<JiazhangkongbaiyemianEntity>(params).getPage(),new QueryWrapper<JiazhangkongbaiyemianEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<JiazhangkongbaiyemianEntity> wrapper) {
        Page<JiazhangkongbaiyemianEntity> page = baseMapper.selectPage(new Query<JiazhangkongbaiyemianEntity>(params).getPage(),wrapper);
        Page<JiazhangkongbaiyemianView> pageVo = (Page<JiazhangkongbaiyemianView>)page.convert(JiazhangkongbaiyemianView::new);
	    return new PageUtils(pageVo);
 	}
    
	@Override
	public List<JiazhangkongbaiyemianView> selectListView(QueryWrapper<JiazhangkongbaiyemianEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public JiazhangkongbaiyemianView selectView(QueryWrapper<JiazhangkongbaiyemianEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
