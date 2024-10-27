package com.cl.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import jakarta.servlet.http.HttpServletRequest;

import com.cl.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cl.annotation.IgnoreAuth;

import com.cl.entity.JiazhangyuyuejiaoshiEntity;
import com.cl.entity.view.JiazhangyuyuejiaoshiView;

import com.cl.service.JiazhangyuyuejiaoshiService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 家长预约教师
 * 后端接口
 * @author 
 * @email 
 * @date 2024-10-24 16:43:17
 */
@RestController
@RequestMapping("/jiazhangyuyuejiaoshi")
public class JiazhangyuyuejiaoshiController {
    @Autowired
    private JiazhangyuyuejiaoshiService jiazhangyuyuejiaoshiService;







    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,JiazhangyuyuejiaoshiEntity jiazhangyuyuejiaoshi,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("jiaoshi")) {
			jiazhangyuyuejiaoshi.setJiaoshigonghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("jiazhang")) {
			jiazhangyuyuejiaoshi.setJiazhangzhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("xuesheng")) {
			jiazhangyuyuejiaoshi.setXuehao((String)request.getSession().getAttribute("username"));
		}
        QueryWrapper<JiazhangyuyuejiaoshiEntity> ew = new QueryWrapper<JiazhangyuyuejiaoshiEntity>();

		PageUtils page = jiazhangyuyuejiaoshiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiazhangyuyuejiaoshi), params), params));

        return R.ok().put("data", page);
    }







    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,JiazhangyuyuejiaoshiEntity jiazhangyuyuejiaoshi,
		HttpServletRequest request){
        QueryWrapper<JiazhangyuyuejiaoshiEntity> ew = new QueryWrapper<JiazhangyuyuejiaoshiEntity>();

		PageUtils page = jiazhangyuyuejiaoshiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiazhangyuyuejiaoshi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( JiazhangyuyuejiaoshiEntity jiazhangyuyuejiaoshi){
       	QueryWrapper<JiazhangyuyuejiaoshiEntity> ew = new QueryWrapper<JiazhangyuyuejiaoshiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( jiazhangyuyuejiaoshi, "jiazhangyuyuejiaoshi"));
        return R.ok().put("data", jiazhangyuyuejiaoshiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(JiazhangyuyuejiaoshiEntity jiazhangyuyuejiaoshi){
        QueryWrapper< JiazhangyuyuejiaoshiEntity> ew = new QueryWrapper< JiazhangyuyuejiaoshiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( jiazhangyuyuejiaoshi, "jiazhangyuyuejiaoshi"));
		JiazhangyuyuejiaoshiView jiazhangyuyuejiaoshiView =  jiazhangyuyuejiaoshiService.selectView(ew);
		return R.ok("search parent appointment teacher success").put("data", jiazhangyuyuejiaoshiView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JiazhangyuyuejiaoshiEntity jiazhangyuyuejiaoshi = jiazhangyuyuejiaoshiService.getById(id);
		jiazhangyuyuejiaoshi = jiazhangyuyuejiaoshiService.selectView(new QueryWrapper<JiazhangyuyuejiaoshiEntity>().eq("id", id));
        return R.ok().put("data", jiazhangyuyuejiaoshi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        JiazhangyuyuejiaoshiEntity jiazhangyuyuejiaoshi = jiazhangyuyuejiaoshiService.getById(id);
		jiazhangyuyuejiaoshi = jiazhangyuyuejiaoshiService.selectView(new QueryWrapper<JiazhangyuyuejiaoshiEntity>().eq("id", id));
        return R.ok().put("data", jiazhangyuyuejiaoshi);
    }




    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody JiazhangyuyuejiaoshiEntity jiazhangyuyuejiaoshi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(jiazhangyuyuejiaoshi);
        jiazhangyuyuejiaoshiService.save(jiazhangyuyuejiaoshi);
        return R.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody JiazhangyuyuejiaoshiEntity jiazhangyuyuejiaoshi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(jiazhangyuyuejiaoshi);
        jiazhangyuyuejiaoshiService.save(jiazhangyuyuejiaoshi);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody JiazhangyuyuejiaoshiEntity jiazhangyuyuejiaoshi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(jiazhangyuyuejiaoshi);
        jiazhangyuyuejiaoshiService.updateById(jiazhangyuyuejiaoshi);//全部更新
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        jiazhangyuyuejiaoshiService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }











}
