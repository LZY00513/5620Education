package com.cl.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cl.utils.PageUtils;
import com.cl.utils.Query;


import com.cl.dao.ZuoyexinxiDao;
import com.cl.entity.ZuoyexinxiEntity;
import com.cl.service.ZuoyexinxiService;
import com.cl.entity.view.ZuoyexinxiView;

@Service("zuoyexinxiService")
public class ZuoyexinxiServiceImpl extends ServiceImpl<ZuoyexinxiDao, ZuoyexinxiEntity> implements ZuoyexinxiService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZuoyexinxiEntity> page = baseMapper.selectPage(new Query<ZuoyexinxiEntity>(params).getPage(),new QueryWrapper<ZuoyexinxiEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<ZuoyexinxiEntity> wrapper) {
        Page<ZuoyexinxiEntity> page = baseMapper.selectPage(new Query<ZuoyexinxiEntity>(params).getPage(),wrapper);
        Page<ZuoyexinxiView> pageVo = (Page<ZuoyexinxiView>)page.convert(ZuoyexinxiView::new);
	    return new PageUtils(pageVo);
 	}
    
	@Override
	public List<ZuoyexinxiView> selectListView(QueryWrapper<ZuoyexinxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ZuoyexinxiView selectView(QueryWrapper<ZuoyexinxiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
