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

import com.cl.entity.ZuoyepigaiEntity;
import com.cl.entity.view.ZuoyepigaiView;

import com.cl.service.ZuoyepigaiService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 作业批改
 * 后端接口
 * @author 
 * @email 
 * @date 2024-10-24 16:43:16
 */
@RestController
@RequestMapping("/zuoyepigai")
public class ZuoyepigaiController {
    @Autowired
    private ZuoyepigaiService zuoyepigaiService;







    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ZuoyepigaiEntity zuoyepigai,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("jiaoshi")) {
			zuoyepigai.setJiaoshigonghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("xuesheng")) {
			zuoyepigai.setXuehao((String)request.getSession().getAttribute("username"));
		}
        QueryWrapper<ZuoyepigaiEntity> ew = new QueryWrapper<ZuoyepigaiEntity>();

		PageUtils page = zuoyepigaiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zuoyepigai), params), params));

        return R.ok().put("data", page);
    }







    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ZuoyepigaiEntity zuoyepigai,
		HttpServletRequest request){
        QueryWrapper<ZuoyepigaiEntity> ew = new QueryWrapper<ZuoyepigaiEntity>();

		PageUtils page = zuoyepigaiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zuoyepigai), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ZuoyepigaiEntity zuoyepigai){
       	QueryWrapper<ZuoyepigaiEntity> ew = new QueryWrapper<ZuoyepigaiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( zuoyepigai, "zuoyepigai"));
        return R.ok().put("data", zuoyepigaiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ZuoyepigaiEntity zuoyepigai){
        QueryWrapper< ZuoyepigaiEntity> ew = new QueryWrapper< ZuoyepigaiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( zuoyepigai, "zuoyepigai"));
		ZuoyepigaiView zuoyepigaiView =  zuoyepigaiService.selectView(ew);
		return R.ok("search homework grade success").put("data", zuoyepigaiView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ZuoyepigaiEntity zuoyepigai = zuoyepigaiService.getById(id);
		zuoyepigai = zuoyepigaiService.selectView(new QueryWrapper<ZuoyepigaiEntity>().eq("id", id));
        return R.ok().put("data", zuoyepigai);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ZuoyepigaiEntity zuoyepigai = zuoyepigaiService.getById(id);
		zuoyepigai = zuoyepigaiService.selectView(new QueryWrapper<ZuoyepigaiEntity>().eq("id", id));
        return R.ok().put("data", zuoyepigai);
    }




    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ZuoyepigaiEntity zuoyepigai, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(zuoyepigai);
        zuoyepigaiService.save(zuoyepigai);
        return R.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ZuoyepigaiEntity zuoyepigai, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(zuoyepigai);
        zuoyepigaiService.save(zuoyepigai);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ZuoyepigaiEntity zuoyepigai, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zuoyepigai);
        zuoyepigaiService.updateById(zuoyepigai);//全部更新
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        zuoyepigaiService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }











}
