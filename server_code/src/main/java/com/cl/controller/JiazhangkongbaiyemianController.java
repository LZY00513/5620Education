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

import com.cl.entity.JiazhangkongbaiyemianEntity;
import com.cl.entity.view.JiazhangkongbaiyemianView;

import com.cl.service.JiazhangkongbaiyemianService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 家长空白页面
 * 后端接口
 * @author 
 * @email 
 * @date 2024-10-24 16:43:17
 */
@RestController
@RequestMapping("/jiazhangkongbaiyemian")
public class JiazhangkongbaiyemianController {
    @Autowired
    private JiazhangkongbaiyemianService jiazhangkongbaiyemianService;







    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,JiazhangkongbaiyemianEntity jiazhangkongbaiyemian,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("jiazhang")) {
			jiazhangkongbaiyemian.setJiazhangzhanghao((String)request.getSession().getAttribute("username"));
		}
        QueryWrapper<JiazhangkongbaiyemianEntity> ew = new QueryWrapper<JiazhangkongbaiyemianEntity>();

		PageUtils page = jiazhangkongbaiyemianService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiazhangkongbaiyemian), params), params));

        return R.ok().put("data", page);
    }







    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,JiazhangkongbaiyemianEntity jiazhangkongbaiyemian,
		HttpServletRequest request){
        QueryWrapper<JiazhangkongbaiyemianEntity> ew = new QueryWrapper<JiazhangkongbaiyemianEntity>();

		PageUtils page = jiazhangkongbaiyemianService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiazhangkongbaiyemian), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( JiazhangkongbaiyemianEntity jiazhangkongbaiyemian){
       	QueryWrapper<JiazhangkongbaiyemianEntity> ew = new QueryWrapper<JiazhangkongbaiyemianEntity>();
      	ew.allEq(MPUtil.allEQMapPre( jiazhangkongbaiyemian, "jiazhangkongbaiyemian"));
        return R.ok().put("data", jiazhangkongbaiyemianService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(JiazhangkongbaiyemianEntity jiazhangkongbaiyemian){
        QueryWrapper< JiazhangkongbaiyemianEntity> ew = new QueryWrapper< JiazhangkongbaiyemianEntity>();
 		ew.allEq(MPUtil.allEQMapPre( jiazhangkongbaiyemian, "jiazhangkongbaiyemian"));
		JiazhangkongbaiyemianView jiazhangkongbaiyemianView =  jiazhangkongbaiyemianService.selectView(ew);
		return R.ok("查询家长空白页面成功").put("data", jiazhangkongbaiyemianView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JiazhangkongbaiyemianEntity jiazhangkongbaiyemian = jiazhangkongbaiyemianService.getById(id);
		jiazhangkongbaiyemian = jiazhangkongbaiyemianService.selectView(new QueryWrapper<JiazhangkongbaiyemianEntity>().eq("id", id));
        return R.ok().put("data", jiazhangkongbaiyemian);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        JiazhangkongbaiyemianEntity jiazhangkongbaiyemian = jiazhangkongbaiyemianService.getById(id);
		jiazhangkongbaiyemian = jiazhangkongbaiyemianService.selectView(new QueryWrapper<JiazhangkongbaiyemianEntity>().eq("id", id));
        return R.ok().put("data", jiazhangkongbaiyemian);
    }




    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody JiazhangkongbaiyemianEntity jiazhangkongbaiyemian, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(jiazhangkongbaiyemian);
        jiazhangkongbaiyemianService.save(jiazhangkongbaiyemian);
        return R.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody JiazhangkongbaiyemianEntity jiazhangkongbaiyemian, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(jiazhangkongbaiyemian);
        jiazhangkongbaiyemianService.save(jiazhangkongbaiyemian);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody JiazhangkongbaiyemianEntity jiazhangkongbaiyemian, HttpServletRequest request){
        //ValidatorUtils.validateEntity(jiazhangkongbaiyemian);
        jiazhangkongbaiyemianService.updateById(jiazhangkongbaiyemian);//全部更新
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        jiazhangkongbaiyemianService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }











}
