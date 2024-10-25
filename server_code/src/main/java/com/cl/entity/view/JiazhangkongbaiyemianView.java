package com.cl.entity.view;

import com.cl.entity.JiazhangkongbaiyemianEntity;

import com.baomidou.mybatisplus.annotation.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.cl.utils.EncryptUtil;
 

/**
 * 家长空白页面
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2024-10-24 16:43:17
 */
@TableName("jiazhangkongbaiyemian")
public class JiazhangkongbaiyemianView  extends JiazhangkongbaiyemianEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public JiazhangkongbaiyemianView(){
	}
 
 	public JiazhangkongbaiyemianView(JiazhangkongbaiyemianEntity jiazhangkongbaiyemianEntity){
 	try {
			BeanUtils.copyProperties(this, jiazhangkongbaiyemianEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}



}
