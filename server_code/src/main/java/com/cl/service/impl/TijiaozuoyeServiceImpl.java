package com.cl.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cl.utils.PageUtils;
import com.cl.utils.Query;


import com.cl.dao.TijiaozuoyeDao;
import com.cl.entity.TijiaozuoyeEntity;
import com.cl.service.TijiaozuoyeService;
import com.cl.entity.view.TijiaozuoyeView;

@Service("tijiaozuoyeService")
public class TijiaozuoyeServiceImpl extends ServiceImpl<TijiaozuoyeDao, TijiaozuoyeEntity> implements TijiaozuoyeService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<TijiaozuoyeEntity> page = baseMapper.selectPage(new Query<TijiaozuoyeEntity>(params).getPage(),new QueryWrapper<TijiaozuoyeEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<TijiaozuoyeEntity> wrapper) {
        Page<TijiaozuoyeEntity> page = baseMapper.selectPage(new Query<TijiaozuoyeEntity>(params).getPage(),wrapper);
        Page<TijiaozuoyeView> pageVo = (Page<TijiaozuoyeView>)page.convert(TijiaozuoyeView::new);
	    return new PageUtils(pageVo);
 	}
    
	@Override
	public List<TijiaozuoyeView> selectListView(QueryWrapper<TijiaozuoyeEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public TijiaozuoyeView selectView(QueryWrapper<TijiaozuoyeEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
