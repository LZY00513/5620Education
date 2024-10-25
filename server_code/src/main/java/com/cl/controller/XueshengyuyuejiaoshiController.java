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

import com.cl.entity.XueshengyuyuejiaoshiEntity;
import com.cl.entity.view.XueshengyuyuejiaoshiView;

import com.cl.service.XueshengyuyuejiaoshiService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 学生预约教师
 * 后端接口
 * @author 
 * @email 
 * @date 2024-10-24 16:43:17
 */
@RestController
@RequestMapping("/xueshengyuyuejiaoshi")
public class XueshengyuyuejiaoshiController {
    @Autowired
    private XueshengyuyuejiaoshiService xueshengyuyuejiaoshiService;







    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,XueshengyuyuejiaoshiEntity xueshengyuyuejiaoshi,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("jiaoshi")) {
			xueshengyuyuejiaoshi.setJiaoshigonghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("xuesheng")) {
			xueshengyuyuejiaoshi.setXuehao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("jiazhang")) {
			xueshengyuyuejiaoshi.setJiazhangzhanghao((String)request.getSession().getAttribute("username"));
		}
        QueryWrapper<XueshengyuyuejiaoshiEntity> ew = new QueryWrapper<XueshengyuyuejiaoshiEntity>();

		PageUtils page = xueshengyuyuejiaoshiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xueshengyuyuejiaoshi), params), params));

        return R.ok().put("data", page);
    }







    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,XueshengyuyuejiaoshiEntity xueshengyuyuejiaoshi,
		HttpServletRequest request){
        QueryWrapper<XueshengyuyuejiaoshiEntity> ew = new QueryWrapper<XueshengyuyuejiaoshiEntity>();

		PageUtils page = xueshengyuyuejiaoshiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xueshengyuyuejiaoshi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( XueshengyuyuejiaoshiEntity xueshengyuyuejiaoshi){
       	QueryWrapper<XueshengyuyuejiaoshiEntity> ew = new QueryWrapper<XueshengyuyuejiaoshiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( xueshengyuyuejiaoshi, "xueshengyuyuejiaoshi"));
        return R.ok().put("data", xueshengyuyuejiaoshiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(XueshengyuyuejiaoshiEntity xueshengyuyuejiaoshi){
        QueryWrapper< XueshengyuyuejiaoshiEntity> ew = new QueryWrapper< XueshengyuyuejiaoshiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( xueshengyuyuejiaoshi, "xueshengyuyuejiaoshi"));
		XueshengyuyuejiaoshiView xueshengyuyuejiaoshiView =  xueshengyuyuejiaoshiService.selectView(ew);
		return R.ok("查询学生预约教师成功").put("data", xueshengyuyuejiaoshiView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        XueshengyuyuejiaoshiEntity xueshengyuyuejiaoshi = xueshengyuyuejiaoshiService.getById(id);
		xueshengyuyuejiaoshi = xueshengyuyuejiaoshiService.selectView(new QueryWrapper<XueshengyuyuejiaoshiEntity>().eq("id", id));
        return R.ok().put("data", xueshengyuyuejiaoshi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        XueshengyuyuejiaoshiEntity xueshengyuyuejiaoshi = xueshengyuyuejiaoshiService.getById(id);
		xueshengyuyuejiaoshi = xueshengyuyuejiaoshiService.selectView(new QueryWrapper<XueshengyuyuejiaoshiEntity>().eq("id", id));
        return R.ok().put("data", xueshengyuyuejiaoshi);
    }




    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody XueshengyuyuejiaoshiEntity xueshengyuyuejiaoshi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(xueshengyuyuejiaoshi);
        xueshengyuyuejiaoshiService.save(xueshengyuyuejiaoshi);
        return R.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody XueshengyuyuejiaoshiEntity xueshengyuyuejiaoshi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(xueshengyuyuejiaoshi);
        xueshengyuyuejiaoshiService.save(xueshengyuyuejiaoshi);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody XueshengyuyuejiaoshiEntity xueshengyuyuejiaoshi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(xueshengyuyuejiaoshi);
        xueshengyuyuejiaoshiService.updateById(xueshengyuyuejiaoshi);//全部更新
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        xueshengyuyuejiaoshiService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }











}
