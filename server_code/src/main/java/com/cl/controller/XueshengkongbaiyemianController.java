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

import com.cl.entity.XueshengkongbaiyemianEntity;
import com.cl.entity.view.XueshengkongbaiyemianView;

import com.cl.service.XueshengkongbaiyemianService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 学生空白页面
 * 后端接口
 * @author 
 * @email 
 * @date 2024-10-24 16:43:17
 */
@RestController
@RequestMapping("/xueshengkongbaiyemian")
public class XueshengkongbaiyemianController {
    @Autowired
    private XueshengkongbaiyemianService xueshengkongbaiyemianService;







    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,XueshengkongbaiyemianEntity xueshengkongbaiyemian,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("xuesheng")) {
			xueshengkongbaiyemian.setXuehao((String)request.getSession().getAttribute("username"));
		}
        QueryWrapper<XueshengkongbaiyemianEntity> ew = new QueryWrapper<XueshengkongbaiyemianEntity>();

		PageUtils page = xueshengkongbaiyemianService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xueshengkongbaiyemian), params), params));

        return R.ok().put("data", page);
    }







    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,XueshengkongbaiyemianEntity xueshengkongbaiyemian,
		HttpServletRequest request){
        QueryWrapper<XueshengkongbaiyemianEntity> ew = new QueryWrapper<XueshengkongbaiyemianEntity>();

		PageUtils page = xueshengkongbaiyemianService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xueshengkongbaiyemian), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( XueshengkongbaiyemianEntity xueshengkongbaiyemian){
       	QueryWrapper<XueshengkongbaiyemianEntity> ew = new QueryWrapper<XueshengkongbaiyemianEntity>();
      	ew.allEq(MPUtil.allEQMapPre( xueshengkongbaiyemian, "xueshengkongbaiyemian"));
        return R.ok().put("data", xueshengkongbaiyemianService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(XueshengkongbaiyemianEntity xueshengkongbaiyemian){
        QueryWrapper< XueshengkongbaiyemianEntity> ew = new QueryWrapper< XueshengkongbaiyemianEntity>();
 		ew.allEq(MPUtil.allEQMapPre( xueshengkongbaiyemian, "xueshengkongbaiyemian"));
		XueshengkongbaiyemianView xueshengkongbaiyemianView =  xueshengkongbaiyemianService.selectView(ew);
		return R.ok("查询学生空白页面成功").put("data", xueshengkongbaiyemianView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        XueshengkongbaiyemianEntity xueshengkongbaiyemian = xueshengkongbaiyemianService.getById(id);
		xueshengkongbaiyemian = xueshengkongbaiyemianService.selectView(new QueryWrapper<XueshengkongbaiyemianEntity>().eq("id", id));
        return R.ok().put("data", xueshengkongbaiyemian);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        XueshengkongbaiyemianEntity xueshengkongbaiyemian = xueshengkongbaiyemianService.getById(id);
		xueshengkongbaiyemian = xueshengkongbaiyemianService.selectView(new QueryWrapper<XueshengkongbaiyemianEntity>().eq("id", id));
        return R.ok().put("data", xueshengkongbaiyemian);
    }




    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody XueshengkongbaiyemianEntity xueshengkongbaiyemian, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(xueshengkongbaiyemian);
        xueshengkongbaiyemianService.save(xueshengkongbaiyemian);
        return R.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody XueshengkongbaiyemianEntity xueshengkongbaiyemian, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(xueshengkongbaiyemian);
        xueshengkongbaiyemianService.save(xueshengkongbaiyemian);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody XueshengkongbaiyemianEntity xueshengkongbaiyemian, HttpServletRequest request){
        //ValidatorUtils.validateEntity(xueshengkongbaiyemian);
        xueshengkongbaiyemianService.updateById(xueshengkongbaiyemian);//全部更新
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        xueshengkongbaiyemianService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }











}
