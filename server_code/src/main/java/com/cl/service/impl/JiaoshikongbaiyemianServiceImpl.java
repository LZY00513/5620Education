package com.cl.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cl.utils.PageUtils;
import com.cl.utils.Query;


import com.cl.dao.JiaoshikongbaiyemianDao;
import com.cl.entity.JiaoshikongbaiyemianEntity;
import com.cl.service.JiaoshikongbaiyemianService;
import com.cl.entity.view.JiaoshikongbaiyemianView;

@Service("jiaoshikongbaiyemianService")
public class JiaoshikongbaiyemianServiceImpl extends ServiceImpl<JiaoshikongbaiyemianDao, JiaoshikongbaiyemianEntity> implements JiaoshikongbaiyemianService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<JiaoshikongbaiyemianEntity> page = baseMapper.selectPage(new Query<JiaoshikongbaiyemianEntity>(params).getPage(),new QueryWrapper<JiaoshikongbaiyemianEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<JiaoshikongbaiyemianEntity> wrapper) {
        Page<JiaoshikongbaiyemianEntity> page = baseMapper.selectPage(new Query<JiaoshikongbaiyemianEntity>(params).getPage(),wrapper);
        Page<JiaoshikongbaiyemianView> pageVo = (Page<JiaoshikongbaiyemianView>)page.convert(JiaoshikongbaiyemianView::new);
	    return new PageUtils(pageVo);
 	}
    
	@Override
	public List<JiaoshikongbaiyemianView> selectListView(QueryWrapper<JiaoshikongbaiyemianEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public JiaoshikongbaiyemianView selectView(QueryWrapper<JiaoshikongbaiyemianEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
