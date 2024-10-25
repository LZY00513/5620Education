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

import com.cl.entity.JiaoshikongbaiyemianEntity;
import com.cl.entity.view.JiaoshikongbaiyemianView;

import com.cl.service.JiaoshikongbaiyemianService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 教师空白页面
 * 后端接口
 * @author 
 * @email 
 * @date 2024-10-24 16:43:17
 */
@RestController
@RequestMapping("/jiaoshikongbaiyemian")
public class JiaoshikongbaiyemianController {
    @Autowired
    private JiaoshikongbaiyemianService jiaoshikongbaiyemianService;







    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,JiaoshikongbaiyemianEntity jiaoshikongbaiyemian,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("jiaoshi")) {
			jiaoshikongbaiyemian.setJiaoshigonghao((String)request.getSession().getAttribute("username"));
		}
        QueryWrapper<JiaoshikongbaiyemianEntity> ew = new QueryWrapper<JiaoshikongbaiyemianEntity>();

		PageUtils page = jiaoshikongbaiyemianService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiaoshikongbaiyemian), params), params));

        return R.ok().put("data", page);
    }







    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,JiaoshikongbaiyemianEntity jiaoshikongbaiyemian,
		HttpServletRequest request){
        QueryWrapper<JiaoshikongbaiyemianEntity> ew = new QueryWrapper<JiaoshikongbaiyemianEntity>();

		PageUtils page = jiaoshikongbaiyemianService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiaoshikongbaiyemian), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( JiaoshikongbaiyemianEntity jiaoshikongbaiyemian){
       	QueryWrapper<JiaoshikongbaiyemianEntity> ew = new QueryWrapper<JiaoshikongbaiyemianEntity>();
      	ew.allEq(MPUtil.allEQMapPre( jiaoshikongbaiyemian, "jiaoshikongbaiyemian"));
        return R.ok().put("data", jiaoshikongbaiyemianService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(JiaoshikongbaiyemianEntity jiaoshikongbaiyemian){
        QueryWrapper< JiaoshikongbaiyemianEntity> ew = new QueryWrapper< JiaoshikongbaiyemianEntity>();
 		ew.allEq(MPUtil.allEQMapPre( jiaoshikongbaiyemian, "jiaoshikongbaiyemian"));
		JiaoshikongbaiyemianView jiaoshikongbaiyemianView =  jiaoshikongbaiyemianService.selectView(ew);
		return R.ok("查询教师空白页面成功").put("data", jiaoshikongbaiyemianView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JiaoshikongbaiyemianEntity jiaoshikongbaiyemian = jiaoshikongbaiyemianService.getById(id);
		jiaoshikongbaiyemian = jiaoshikongbaiyemianService.selectView(new QueryWrapper<JiaoshikongbaiyemianEntity>().eq("id", id));
        return R.ok().put("data", jiaoshikongbaiyemian);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        JiaoshikongbaiyemianEntity jiaoshikongbaiyemian = jiaoshikongbaiyemianService.getById(id);
		jiaoshikongbaiyemian = jiaoshikongbaiyemianService.selectView(new QueryWrapper<JiaoshikongbaiyemianEntity>().eq("id", id));
        return R.ok().put("data", jiaoshikongbaiyemian);
    }




    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody JiaoshikongbaiyemianEntity jiaoshikongbaiyemian, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(jiaoshikongbaiyemian);
        jiaoshikongbaiyemianService.save(jiaoshikongbaiyemian);
        return R.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody JiaoshikongbaiyemianEntity jiaoshikongbaiyemian, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(jiaoshikongbaiyemian);
        jiaoshikongbaiyemianService.save(jiaoshikongbaiyemian);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody JiaoshikongbaiyemianEntity jiaoshikongbaiyemian, HttpServletRequest request){
        //ValidatorUtils.validateEntity(jiaoshikongbaiyemian);
        jiaoshikongbaiyemianService.updateById(jiaoshikongbaiyemian);//全部更新
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        jiaoshikongbaiyemianService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }











}
