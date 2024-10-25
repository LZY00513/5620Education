package com.cl.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cl.utils.PageUtils;
import com.cl.utils.Query;


import com.cl.dao.ForumDao;
import com.cl.entity.ForumEntity;
import com.cl.service.ForumService;
import com.cl.entity.view.ForumView;

@Service("forumService")
public class ForumServiceImpl extends ServiceImpl<ForumDao, ForumEntity> implements ForumService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ForumEntity> page = baseMapper.selectPage(new Query<ForumEntity>(params).getPage(),new QueryWrapper<ForumEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<ForumEntity> wrapper) {
        Page<ForumEntity> page = baseMapper.selectPage(new Query<ForumEntity>(params).getPage(),wrapper);
        Page<ForumView> pageVo = (Page<ForumView>)page.convert(ForumView::new);
	    return new PageUtils(pageVo);
 	}
    
	@Override
	public List<ForumView> selectListView(QueryWrapper<ForumEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ForumView selectView(QueryWrapper<ForumEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
