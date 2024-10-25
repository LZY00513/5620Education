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

import com.cl.entity.ZuoyexinxiEntity;
import com.cl.entity.view.ZuoyexinxiView;

import com.cl.service.ZuoyexinxiService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 作业信息
 * 后端接口
 * @author 
 * @email 
 * @date 2024-10-24 16:43:16
 */
@RestController
@RequestMapping("/zuoyexinxi")
public class ZuoyexinxiController {
    @Autowired
    private ZuoyexinxiService zuoyexinxiService;







    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ZuoyexinxiEntity zuoyexinxi,
		HttpServletRequest request){
    	if(!request.getSession().getAttribute("role").toString().equals("管理员")) {
    		zuoyexinxi.setUserid((Long)request.getSession().getAttribute("userId"));
    	}
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("jiaoshi")) {
			zuoyexinxi.setJiaoshigonghao((String)request.getSession().getAttribute("username"));
		}
        QueryWrapper<ZuoyexinxiEntity> ew = new QueryWrapper<ZuoyexinxiEntity>();

		PageUtils page = zuoyexinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zuoyexinxi), params), params));

        return R.ok().put("data", page);
    }







    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ZuoyexinxiEntity zuoyexinxi,
		HttpServletRequest request){
        QueryWrapper<ZuoyexinxiEntity> ew = new QueryWrapper<ZuoyexinxiEntity>();

		PageUtils page = zuoyexinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zuoyexinxi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ZuoyexinxiEntity zuoyexinxi){
       	QueryWrapper<ZuoyexinxiEntity> ew = new QueryWrapper<ZuoyexinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( zuoyexinxi, "zuoyexinxi"));
        return R.ok().put("data", zuoyexinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ZuoyexinxiEntity zuoyexinxi){
        QueryWrapper< ZuoyexinxiEntity> ew = new QueryWrapper< ZuoyexinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( zuoyexinxi, "zuoyexinxi"));
		ZuoyexinxiView zuoyexinxiView =  zuoyexinxiService.selectView(ew);
		return R.ok("查询作业信息成功").put("data", zuoyexinxiView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ZuoyexinxiEntity zuoyexinxi = zuoyexinxiService.getById(id);
		zuoyexinxi = zuoyexinxiService.selectView(new QueryWrapper<ZuoyexinxiEntity>().eq("id", id));
        return R.ok().put("data", zuoyexinxi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ZuoyexinxiEntity zuoyexinxi = zuoyexinxiService.getById(id);
		zuoyexinxi = zuoyexinxiService.selectView(new QueryWrapper<ZuoyexinxiEntity>().eq("id", id));
        return R.ok().put("data", zuoyexinxi);
    }




    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ZuoyexinxiEntity zuoyexinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(zuoyexinxi);
    	zuoyexinxi.setUserid((Long)request.getSession().getAttribute("userId"));
        zuoyexinxiService.save(zuoyexinxi);
        return R.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ZuoyexinxiEntity zuoyexinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(zuoyexinxi);
        zuoyexinxiService.save(zuoyexinxi);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ZuoyexinxiEntity zuoyexinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zuoyexinxi);
        zuoyexinxiService.updateById(zuoyexinxi);//全部更新
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        zuoyexinxiService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }











}
