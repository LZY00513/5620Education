package com.cl.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cl.utils.PageUtils;
import com.cl.utils.Query;


import com.cl.dao.JiaoxueziyuanDao;
import com.cl.entity.JiaoxueziyuanEntity;
import com.cl.service.JiaoxueziyuanService;
import com.cl.entity.view.JiaoxueziyuanView;

@Service("jiaoxueziyuanService")
public class JiaoxueziyuanServiceImpl extends ServiceImpl<JiaoxueziyuanDao, JiaoxueziyuanEntity> implements JiaoxueziyuanService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<JiaoxueziyuanEntity> page = baseMapper.selectPage(new Query<JiaoxueziyuanEntity>(params).getPage(),new QueryWrapper<JiaoxueziyuanEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<JiaoxueziyuanEntity> wrapper) {
        Page<JiaoxueziyuanEntity> page = baseMapper.selectPage(new Query<JiaoxueziyuanEntity>(params).getPage(),wrapper);
        Page<JiaoxueziyuanView> pageVo = (Page<JiaoxueziyuanView>)page.convert(JiaoxueziyuanView::new);
	    return new PageUtils(pageVo);
 	}
    
	@Override
	public List<JiaoxueziyuanView> selectListView(QueryWrapper<JiaoxueziyuanEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public JiaoxueziyuanView selectView(QueryWrapper<JiaoxueziyuanEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, QueryWrapper<JiaoxueziyuanEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, QueryWrapper<JiaoxueziyuanEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, QueryWrapper<JiaoxueziyuanEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }




}
