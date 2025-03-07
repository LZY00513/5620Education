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

import com.cl.entity.TijiaozuoyeEntity;
import com.cl.entity.view.TijiaozuoyeView;

import com.cl.service.TijiaozuoyeService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 提交作业
 * 后端接口
 * @author 
 * @email 
 * @date 2024-10-24 16:43:16
 */
@RestController
@RequestMapping("/tijiaozuoye")
public class TijiaozuoyeController {
    @Autowired
    private TijiaozuoyeService tijiaozuoyeService;







    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,TijiaozuoyeEntity tijiaozuoye,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("jiaoshi")) {
			tijiaozuoye.setJiaoshigonghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("xuesheng")) {
			tijiaozuoye.setXuehao((String)request.getSession().getAttribute("username"));
		}
        QueryWrapper<TijiaozuoyeEntity> ew = new QueryWrapper<TijiaozuoyeEntity>();

		PageUtils page = tijiaozuoyeService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, tijiaozuoye), params), params));

        return R.ok().put("data", page);
    }







    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,TijiaozuoyeEntity tijiaozuoye,
		HttpServletRequest request){
        QueryWrapper<TijiaozuoyeEntity> ew = new QueryWrapper<TijiaozuoyeEntity>();

		PageUtils page = tijiaozuoyeService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, tijiaozuoye), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( TijiaozuoyeEntity tijiaozuoye){
       	QueryWrapper<TijiaozuoyeEntity> ew = new QueryWrapper<TijiaozuoyeEntity>();
      	ew.allEq(MPUtil.allEQMapPre( tijiaozuoye, "tijiaozuoye"));
        return R.ok().put("data", tijiaozuoyeService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(TijiaozuoyeEntity tijiaozuoye){
        QueryWrapper< TijiaozuoyeEntity> ew = new QueryWrapper< TijiaozuoyeEntity>();
 		ew.allEq(MPUtil.allEQMapPre( tijiaozuoye, "tijiaozuoye"));
		TijiaozuoyeView tijiaozuoyeView =  tijiaozuoyeService.selectView(ew);
		return R.ok("search query success").put("data", tijiaozuoyeView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        TijiaozuoyeEntity tijiaozuoye = tijiaozuoyeService.getById(id);
		tijiaozuoye = tijiaozuoyeService.selectView(new QueryWrapper<TijiaozuoyeEntity>().eq("id", id));
        return R.ok().put("data", tijiaozuoye);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        TijiaozuoyeEntity tijiaozuoye = tijiaozuoyeService.getById(id);
		tijiaozuoye = tijiaozuoyeService.selectView(new QueryWrapper<TijiaozuoyeEntity>().eq("id", id));
        return R.ok().put("data", tijiaozuoye);
    }




    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody TijiaozuoyeEntity tijiaozuoye, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(tijiaozuoye);
        tijiaozuoyeService.save(tijiaozuoye);
        return R.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody TijiaozuoyeEntity tijiaozuoye, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(tijiaozuoye);
        tijiaozuoyeService.save(tijiaozuoye);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody TijiaozuoyeEntity tijiaozuoye, HttpServletRequest request){
        //ValidatorUtils.validateEntity(tijiaozuoye);
        tijiaozuoyeService.updateById(tijiaozuoye);//全部更新
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        tijiaozuoyeService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }











}
