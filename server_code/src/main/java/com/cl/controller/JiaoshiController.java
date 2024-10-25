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

import com.cl.entity.JiaoshiEntity;
import com.cl.entity.view.JiaoshiView;

import com.cl.service.JiaoshiService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 教师
 * 后端接口
 * @author 
 * @email 
 * @date 2024-10-24 16:43:16
 */
@RestController
@RequestMapping("/jiaoshi")
public class JiaoshiController {
    @Autowired
    private JiaoshiService jiaoshiService;



	@Autowired
	private TokenService tokenService;
	
	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		JiaoshiEntity u = jiaoshiService.getOne(new QueryWrapper<JiaoshiEntity>().eq("jiaoshigonghao", username));
        if(u==null || !u.getMima().equals(password)) {
            return R.error("账号或密码不正确");
        }
        if(!"是".equals(u.getSfsh())) return R.error("账号已锁定，请联系管理员审核。");
		String token = tokenService.generateToken(u.getId(), username,"jiaoshi",  "教师" );
		return R.ok().put("token", token);
	}



	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody JiaoshiEntity jiaoshi){
    	//ValidatorUtils.validateEntity(jiaoshi);
    	JiaoshiEntity u = jiaoshiService.getOne(new QueryWrapper<JiaoshiEntity>().eq("jiaoshigonghao", jiaoshi.getJiaoshigonghao()));
		if(u!=null) {
			return R.error("注册用户已存在");
		}
		Long uId = new Date().getTime();
		jiaoshi.setId(uId);
        jiaoshiService.save(jiaoshi);
        return R.ok();
    }


	/**
	 * 退出
	 */
	@RequestMapping("/logout")
	public R logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return R.ok("退出成功");
	}

	/**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request){
    	Long id = (Long)request.getSession().getAttribute("userId");
        return R.ok().put("data", jiaoshiService.selectView(new QueryWrapper<JiaoshiEntity>().eq("id", id)));
    }

    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	JiaoshiEntity u = jiaoshiService.getOne(new QueryWrapper<JiaoshiEntity>().eq("jiaoshigonghao", username));
    	if(u==null) {
    		return R.error("账号不存在");
    	}
        u.setMima("123456");
        jiaoshiService.updateById(u);
        return R.ok("密码已重置为：123456");
    }




    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,JiaoshiEntity jiaoshi,
		HttpServletRequest request){
        QueryWrapper<JiaoshiEntity> ew = new QueryWrapper<JiaoshiEntity>();

		PageUtils page = jiaoshiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiaoshi), params), params));

        return R.ok().put("data", page);
    }







    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,JiaoshiEntity jiaoshi,
		HttpServletRequest request){
        QueryWrapper<JiaoshiEntity> ew = new QueryWrapper<JiaoshiEntity>();

		PageUtils page = jiaoshiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiaoshi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( JiaoshiEntity jiaoshi){
       	QueryWrapper<JiaoshiEntity> ew = new QueryWrapper<JiaoshiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( jiaoshi, "jiaoshi"));
        return R.ok().put("data", jiaoshiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(JiaoshiEntity jiaoshi){
        QueryWrapper< JiaoshiEntity> ew = new QueryWrapper< JiaoshiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( jiaoshi, "jiaoshi"));
		JiaoshiView jiaoshiView =  jiaoshiService.selectView(ew);
		return R.ok("查询教师成功").put("data", jiaoshiView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JiaoshiEntity jiaoshi = jiaoshiService.getById(id);
		jiaoshi = jiaoshiService.selectView(new QueryWrapper<JiaoshiEntity>().eq("id", id));
        return R.ok().put("data", jiaoshi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        JiaoshiEntity jiaoshi = jiaoshiService.getById(id);
		jiaoshi = jiaoshiService.selectView(new QueryWrapper<JiaoshiEntity>().eq("id", id));
        return R.ok().put("data", jiaoshi);
    }




    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody JiaoshiEntity jiaoshi, HttpServletRequest request){
        if(jiaoshiService.count(new QueryWrapper<JiaoshiEntity>().eq("jiaoshigonghao", jiaoshi.getJiaoshigonghao()))>0) {
            return R.error("教师工号已存在");
        }
    	jiaoshi.setId(new Date().getTime()+(long)Math.floor(Math.random()*1000));
    	//ValidatorUtils.validateEntity(jiaoshi);
    	JiaoshiEntity u = jiaoshiService.getOne(new QueryWrapper<JiaoshiEntity>().eq("jiaoshigonghao", jiaoshi.getJiaoshigonghao()));
		if(u!=null) {
			return R.error("用户已存在");
		}
		jiaoshi.setId(new Date().getTime());
        jiaoshiService.save(jiaoshi);
        return R.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody JiaoshiEntity jiaoshi, HttpServletRequest request){
        if(jiaoshiService.count(new QueryWrapper<JiaoshiEntity>().eq("jiaoshigonghao", jiaoshi.getJiaoshigonghao()))>0) {
            return R.error("教师工号已存在");
        }
    	jiaoshi.setId(new Date().getTime()+(long)Math.floor(Math.random()*1000));
    	//ValidatorUtils.validateEntity(jiaoshi);
    	JiaoshiEntity u = jiaoshiService.getOne(new QueryWrapper<JiaoshiEntity>().eq("jiaoshigonghao", jiaoshi.getJiaoshigonghao()));
		if(u!=null) {
			return R.error("用户已存在");
		}
		jiaoshi.setId(new Date().getTime());
        jiaoshiService.save(jiaoshi);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody JiaoshiEntity jiaoshi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(jiaoshi);
        jiaoshiService.updateById(jiaoshi);//全部更新
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<JiaoshiEntity> list = new ArrayList<JiaoshiEntity>();
        for(Long id : ids) {
            JiaoshiEntity jiaoshi = jiaoshiService.getById(id);
            jiaoshi.setSfsh(sfsh);
            jiaoshi.setShhf(shhf);
            list.add(jiaoshi);
        }
        jiaoshiService.updateBatchById(list);
        return R.ok();
    }




    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        jiaoshiService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }











}
