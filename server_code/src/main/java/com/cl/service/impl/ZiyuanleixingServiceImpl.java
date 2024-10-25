package com.cl.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cl.utils.PageUtils;
import com.cl.utils.Query;


import com.cl.dao.ZiyuanleixingDao;
import com.cl.entity.ZiyuanleixingEntity;
import com.cl.service.ZiyuanleixingService;
import com.cl.entity.view.ZiyuanleixingView;

@Service("ziyuanleixingService")
public class ZiyuanleixingServiceImpl extends ServiceImpl<ZiyuanleixingDao, ZiyuanleixingEntity> implements ZiyuanleixingService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZiyuanleixingEntity> page = baseMapper.selectPage(new Query<ZiyuanleixingEntity>(params).getPage(),new QueryWrapper<ZiyuanleixingEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<ZiyuanleixingEntity> wrapper) {
        Page<ZiyuanleixingEntity> page = baseMapper.selectPage(new Query<ZiyuanleixingEntity>(params).getPage(),wrapper);
        Page<ZiyuanleixingView> pageVo = (Page<ZiyuanleixingView>)page.convert(ZiyuanleixingView::new);
	    return new PageUtils(pageVo);
 	}
    
	@Override
	public List<ZiyuanleixingView> selectListView(QueryWrapper<ZiyuanleixingEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ZiyuanleixingView selectView(QueryWrapper<ZiyuanleixingEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
