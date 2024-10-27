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

import com.cl.entity.XueshengEntity;
import com.cl.entity.view.XueshengView;

import com.cl.service.XueshengService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 学生
 * 后端接口
 * @author 
 * @email 
 * @date 2024-10-24 16:43:16
 */
@RestController
@RequestMapping("/xuesheng")
public class XueshengController {
    @Autowired
    private XueshengService xueshengService;



	@Autowired
	private TokenService tokenService;
	
	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		XueshengEntity u = xueshengService.getOne(new QueryWrapper<XueshengEntity>().eq("xuehao", username));
        if(u==null || !u.getMima().equals(password)) {
            return R.error("id or password error");
        }
		String token = tokenService.generateToken(u.getId(), username,"xuesheng",  "student" );
		return R.ok().put("token", token);
	}



	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody XueshengEntity xuesheng){
    	//ValidatorUtils.validateEntity(xuesheng);
    	XueshengEntity u = xueshengService.getOne(new QueryWrapper<XueshengEntity>().eq("xuehao", xuesheng.getXuehao()));
		if(u!=null) {
			return R.error("user existed");
		}
		Long uId = new Date().getTime();
		xuesheng.setId(uId);
        xueshengService.save(xuesheng);
        return R.ok();
    }


	/**
	 * 退出
	 */
	@RequestMapping("/logout")
	public R logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return R.ok("exit success");
	}

	/**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request){
    	Long id = (Long)request.getSession().getAttribute("userId");
        return R.ok().put("data", xueshengService.selectView(new QueryWrapper<XueshengEntity>().eq("id", id)));
    }

    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	XueshengEntity u = xueshengService.getOne(new QueryWrapper<XueshengEntity>().eq("xuehao", username));
    	if(u==null) {
    		return R.error("id not exist");
    	}
        u.setMima("123456");
        xueshengService.updateById(u);
        return R.ok("password overlapped as：123456");
    }




    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,XueshengEntity xuesheng,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("jiazhang")) {
			xuesheng.setJiazhangzhanghao((String)request.getSession().getAttribute("username"));
		}
        QueryWrapper<XueshengEntity> ew = new QueryWrapper<XueshengEntity>();

		PageUtils page = xueshengService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xuesheng), params), params));

        return R.ok().put("data", page);
    }







    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,XueshengEntity xuesheng,
		HttpServletRequest request){
        QueryWrapper<XueshengEntity> ew = new QueryWrapper<XueshengEntity>();

		PageUtils page = xueshengService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xuesheng), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( XueshengEntity xuesheng){
       	QueryWrapper<XueshengEntity> ew = new QueryWrapper<XueshengEntity>();
      	ew.allEq(MPUtil.allEQMapPre( xuesheng, "xuesheng"));
        return R.ok().put("data", xueshengService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(XueshengEntity xuesheng){
        QueryWrapper< XueshengEntity> ew = new QueryWrapper< XueshengEntity>();
 		ew.allEq(MPUtil.allEQMapPre( xuesheng, "xuesheng"));
		XueshengView xueshengView =  xueshengService.selectView(ew);
		return R.ok("search student success").put("data", xueshengView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        XueshengEntity xuesheng = xueshengService.getById(id);
		xuesheng = xueshengService.selectView(new QueryWrapper<XueshengEntity>().eq("id", id));
        return R.ok().put("data", xuesheng);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        XueshengEntity xuesheng = xueshengService.getById(id);
		xuesheng = xueshengService.selectView(new QueryWrapper<XueshengEntity>().eq("id", id));
        return R.ok().put("data", xuesheng);
    }




    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody XueshengEntity xuesheng, HttpServletRequest request){
        if(xueshengService.count(new QueryWrapper<XueshengEntity>().eq("xuehao", xuesheng.getXuehao()))>0) {
            return R.error("sid existed");
        }
    	xuesheng.setId(new Date().getTime()+(long)Math.floor(Math.random()*1000));
    	//ValidatorUtils.validateEntity(xuesheng);
    	XueshengEntity u = xueshengService.getOne(new QueryWrapper<XueshengEntity>().eq("xuehao", xuesheng.getXuehao()));
		if(u!=null) {
			return R.error("user existed");
		}
		xuesheng.setId(new Date().getTime());
        xueshengService.save(xuesheng);
        return R.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody XueshengEntity xuesheng, HttpServletRequest request){
        if(xueshengService.count(new QueryWrapper<XueshengEntity>().eq("xuehao", xuesheng.getXuehao()))>0) {
            return R.error("sid existed");
        }
    	xuesheng.setId(new Date().getTime()+(long)Math.floor(Math.random()*1000));
    	//ValidatorUtils.validateEntity(xuesheng);
    	XueshengEntity u = xueshengService.getOne(new QueryWrapper<XueshengEntity>().eq("xuehao", xuesheng.getXuehao()));
		if(u!=null) {
			return R.error("user existed");
		}
		xuesheng.setId(new Date().getTime());
        xueshengService.save(xuesheng);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody XueshengEntity xuesheng, HttpServletRequest request){
        //ValidatorUtils.validateEntity(xuesheng);
        xueshengService.updateById(xuesheng);//全部更新
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        xueshengService.removeBatchByIds(Arrays.asList(ids));
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
        QueryWrapper<XueshengEntity> ew = new QueryWrapper<XueshengEntity>();
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("jiazhang")) {
            ew.eq("jiazhangzhanghao", (String)request.getSession().getAttribute("username"));
		}
        List<Map<String, Object>> result = xueshengService.selectValue(params, ew);
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
        QueryWrapper<XueshengEntity> ew = new QueryWrapper<XueshengEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("jiazhang")) {
            ew.eq("jiazhangzhanghao", (String)request.getSession().getAttribute("username"));
        }
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = xueshengService.selectValue(params, ew);
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
        QueryWrapper<XueshengEntity> ew = new QueryWrapper<XueshengEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("jiazhang")) {
            ew.eq("jiazhangzhanghao", (String)request.getSession().getAttribute("username"));
        }
        List<Map<String, Object>> result = xueshengService.selectTimeStatValue(params, ew);
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
        QueryWrapper<XueshengEntity> ew = new QueryWrapper<XueshengEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("jiazhang")) {
            ew.eq("jiazhangzhanghao", (String)request.getSession().getAttribute("username"));
        }
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = xueshengService.selectTimeStatValue(params, ew);
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
        QueryWrapper<XueshengEntity> ew = new QueryWrapper<XueshengEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("jiazhang")) {
            ew.eq("jiazhangzhanghao", (String)request.getSession().getAttribute("username"));
        }
        List<Map<String, Object>> result = xueshengService.selectGroup(params, ew);
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
    public R count(@RequestParam Map<String, Object> params,XueshengEntity xuesheng, HttpServletRequest request){
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("jiazhang")) {
            xuesheng.setJiazhangzhanghao((String)request.getSession().getAttribute("username"));
        }
        QueryWrapper<XueshengEntity> ew = new QueryWrapper<XueshengEntity>();
    long count = xueshengService.count(MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xuesheng), params), params));
        return R.ok().put("data", count);
    }



}
