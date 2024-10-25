package com.cl.entity.view;

import com.cl.entity.JiazhangyuyuejiaoshiEntity;

import com.baomidou.mybatisplus.annotation.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.cl.utils.EncryptUtil;
 

/**
 * 家长预约教师
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2024-10-24 16:43:17
 */
@TableName("jiazhangyuyuejiaoshi")
public class JiazhangyuyuejiaoshiView  extends JiazhangyuyuejiaoshiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public JiazhangyuyuejiaoshiView(){
	}
 
 	public JiazhangyuyuejiaoshiView(JiazhangyuyuejiaoshiEntity jiazhangyuyuejiaoshiEntity){
 	try {
			BeanUtils.copyProperties(this, jiazhangyuyuejiaoshiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}



}
