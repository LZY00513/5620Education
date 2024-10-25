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

import com.cl.entity.JiaoxueziyuanEntity;
import com.cl.entity.view.JiaoxueziyuanView;

import com.cl.service.JiaoxueziyuanService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;
import java.io.IOException;
import com.cl.service.StoreupService;
import com.cl.entity.StoreupEntity;

/**
 * 教学资源
 * 后端接口
 * @author 
 * @email 
 * @date 2024-10-24 16:43:16
 */
@RestController
@RequestMapping("/jiaoxueziyuan")
public class JiaoxueziyuanController {
    @Autowired
    private JiaoxueziyuanService jiaoxueziyuanService;

    @Autowired
    private StoreupService storeupService;






    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,JiaoxueziyuanEntity jiaoxueziyuan,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("jiaoshi")) {
			jiaoxueziyuan.setJiaoshigonghao((String)request.getSession().getAttribute("username"));
		}
        QueryWrapper<JiaoxueziyuanEntity> ew = new QueryWrapper<JiaoxueziyuanEntity>();

		PageUtils page = jiaoxueziyuanService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiaoxueziyuan), params), params));

        return R.ok().put("data", page);
    }







    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,JiaoxueziyuanEntity jiaoxueziyuan,
		HttpServletRequest request){
        QueryWrapper<JiaoxueziyuanEntity> ew = new QueryWrapper<JiaoxueziyuanEntity>();

		PageUtils page = jiaoxueziyuanService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiaoxueziyuan), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( JiaoxueziyuanEntity jiaoxueziyuan){
       	QueryWrapper<JiaoxueziyuanEntity> ew = new QueryWrapper<JiaoxueziyuanEntity>();
      	ew.allEq(MPUtil.allEQMapPre( jiaoxueziyuan, "jiaoxueziyuan"));
        return R.ok().put("data", jiaoxueziyuanService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(JiaoxueziyuanEntity jiaoxueziyuan){
        QueryWrapper< JiaoxueziyuanEntity> ew = new QueryWrapper< JiaoxueziyuanEntity>();
 		ew.allEq(MPUtil.allEQMapPre( jiaoxueziyuan, "jiaoxueziyuan"));
		JiaoxueziyuanView jiaoxueziyuanView =  jiaoxueziyuanService.selectView(ew);
		return R.ok("查询教学资源成功").put("data", jiaoxueziyuanView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JiaoxueziyuanEntity jiaoxueziyuan = jiaoxueziyuanService.getById(id);
		jiaoxueziyuan = jiaoxueziyuanService.selectView(new QueryWrapper<JiaoxueziyuanEntity>().eq("id", id));
        return R.ok().put("data", jiaoxueziyuan);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        JiaoxueziyuanEntity jiaoxueziyuan = jiaoxueziyuanService.getById(id);
		jiaoxueziyuan = jiaoxueziyuanService.selectView(new QueryWrapper<JiaoxueziyuanEntity>().eq("id", id));
        return R.ok().put("data", jiaoxueziyuan);
    }




    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody JiaoxueziyuanEntity jiaoxueziyuan, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(jiaoxueziyuan);
        jiaoxueziyuanService.save(jiaoxueziyuan);
        return R.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody JiaoxueziyuanEntity jiaoxueziyuan, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(jiaoxueziyuan);
        jiaoxueziyuanService.save(jiaoxueziyuan);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody JiaoxueziyuanEntity jiaoxueziyuan, HttpServletRequest request){
        //ValidatorUtils.validateEntity(jiaoxueziyuan);
        jiaoxueziyuanService.updateById(jiaoxueziyuan);//全部更新
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        jiaoxueziyuanService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }







    /**
     * （按值统计）
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}")
    public R value(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName,HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("yColumn", yColumnName);
        QueryWrapper<JiaoxueziyuanEntity> ew = new QueryWrapper<JiaoxueziyuanEntity>();
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("jiaoshi")) {
            ew.eq("jiaoshigonghao", (String)request.getSession().getAttribute("username"));
		}
        List<Map<String, Object>> result = jiaoxueziyuanService.selectValue(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }

    /**
     * （按值统计(多)）
     */
    @RequestMapping("/valueMul/{xColumnName}")
    public R valueMul(@PathVariable("xColumnName") String xColumnName,@RequestParam String yColumnNameMul, HttpServletRequest request) {
        String[] yColumnNames = yColumnNameMul.split(",");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String,Object>>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        QueryWrapper<JiaoxueziyuanEntity> ew = new QueryWrapper<JiaoxueziyuanEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("jiaoshi")) {
            ew.eq("jiaoshigonghao", (String)request.getSession().getAttribute("username"));
        }
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = jiaoxueziyuanService.selectValue(params, ew);
            for(Map<String, Object> m : result) {
                for(String k : m.keySet()) {
                    if(m.get(k) instanceof Date) {
                        m.put(k, sdf.format((Date)m.get(k)));
                    }
                }
            }
            result2.add(result);
        }
        return R.ok().put("data", result2);
    }

    /**
     * （按值统计）时间统计类型
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}/{timeStatType}")
    public R valueDay(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType,HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("yColumn", yColumnName);
        params.put("timeStatType", timeStatType);
        QueryWrapper<JiaoxueziyuanEntity> ew = new QueryWrapper<JiaoxueziyuanEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("jiaoshi")) {
            ew.eq("jiaoshigonghao", (String)request.getSession().getAttribute("username"));
        }
        List<Map<String, Object>> result = jiaoxueziyuanService.selectTimeStatValue(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }

    /**
     * （按值统计）时间统计类型(多)
     */
    @RequestMapping("/valueMul/{xColumnName}/{timeStatType}")
    public R valueMulDay(@PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType,@RequestParam String yColumnNameMul,HttpServletRequest request) {
        String[] yColumnNames = yColumnNameMul.split(",");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("timeStatType", timeStatType);
        List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String,Object>>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        QueryWrapper<JiaoxueziyuanEntity> ew = new QueryWrapper<JiaoxueziyuanEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("jiaoshi")) {
            ew.eq("jiaoshigonghao", (String)request.getSession().getAttribute("username"));
        }
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = jiaoxueziyuanService.selectTimeStatValue(params, ew);
            for(Map<String, Object> m : result) {
                for(String k : m.keySet()) {
                    if(m.get(k) instanceof Date) {
                        m.put(k, sdf.format((Date)m.get(k)));
                    }
                }
            }
            result2.add(result);
        }
        return R.ok().put("data", result2);
    }

    /**
     * 分组统计
     */
    @RequestMapping("/group/{columnName}")
    public R group(@PathVariable("columnName") String columnName,HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("column", columnName);
        QueryWrapper<JiaoxueziyuanEntity> ew = new QueryWrapper<JiaoxueziyuanEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("jiaoshi")) {
            ew.eq("jiaoshigonghao", (String)request.getSession().getAttribute("username"));
        }
        List<Map<String, Object>> result = jiaoxueziyuanService.selectGroup(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }




    /**
     * 总数量
     */
    @RequestMapping("/count")
    public R count(@RequestParam Map<String, Object> params,JiaoxueziyuanEntity jiaoxueziyuan, HttpServletRequest request){
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("jiaoshi")) {
            jiaoxueziyuan.setJiaoshigonghao((String)request.getSession().getAttribute("username"));
        }
        QueryWrapper<JiaoxueziyuanEntity> ew = new QueryWrapper<JiaoxueziyuanEntity>();
    long count = jiaoxueziyuanService.count(MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiaoxueziyuan), params), params));
        return R.ok().put("data", count);
    }



}
