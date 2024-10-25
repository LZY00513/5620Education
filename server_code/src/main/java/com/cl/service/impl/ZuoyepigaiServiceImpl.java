package com.cl.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cl.utils.PageUtils;
import com.cl.utils.Query;


import com.cl.dao.ZuoyepigaiDao;
import com.cl.entity.ZuoyepigaiEntity;
import com.cl.service.ZuoyepigaiService;
import com.cl.entity.view.ZuoyepigaiView;

@Service("zuoyepigaiService")
public class ZuoyepigaiServiceImpl extends ServiceImpl<ZuoyepigaiDao, ZuoyepigaiEntity> implements ZuoyepigaiService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZuoyepigaiEntity> page = baseMapper.selectPage(new Query<ZuoyepigaiEntity>(params).getPage(),new QueryWrapper<ZuoyepigaiEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<ZuoyepigaiEntity> wrapper) {
        Page<ZuoyepigaiEntity> page = baseMapper.selectPage(new Query<ZuoyepigaiEntity>(params).getPage(),wrapper);
        Page<ZuoyepigaiView> pageVo = (Page<ZuoyepigaiView>)page.convert(ZuoyepigaiView::new);
	    return new PageUtils(pageVo);
 	}
    
	@Override
	public List<ZuoyepigaiView> selectListView(QueryWrapper<ZuoyepigaiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ZuoyepigaiView selectView(QueryWrapper<ZuoyepigaiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
