package com.cl.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cl.utils.PageUtils;
import com.cl.utils.Query;


import com.cl.dao.JiazhangDao;
import com.cl.entity.JiazhangEntity;
import com.cl.service.JiazhangService;
import com.cl.entity.view.JiazhangView;

@Service("jiazhangService")
public class JiazhangServiceImpl extends ServiceImpl<JiazhangDao, JiazhangEntity> implements JiazhangService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<JiazhangEntity> page = baseMapper.selectPage(new Query<JiazhangEntity>(params).getPage(),new QueryWrapper<JiazhangEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<JiazhangEntity> wrapper) {
        Page<JiazhangEntity> page = baseMapper.selectPage(new Query<JiazhangEntity>(params).getPage(),wrapper);
        Page<JiazhangView> pageVo = (Page<JiazhangView>)page.convert(JiazhangView::new);
	    return new PageUtils(pageVo);
 	}
    
	@Override
	public List<JiazhangView> selectListView(QueryWrapper<JiazhangEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public JiazhangView selectView(QueryWrapper<JiazhangEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
