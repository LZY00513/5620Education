package com.cl.entity.view;

import com.cl.entity.XueshengkongbaiyemianEntity;

import com.baomidou.mybatisplus.annotation.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.cl.utils.EncryptUtil;
 

/**
 * 学生空白页面
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2024-10-24 16:43:17
 */
@TableName("xueshengkongbaiyemian")
public class XueshengkongbaiyemianView  extends XueshengkongbaiyemianEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public XueshengkongbaiyemianView(){
	}
 
 	public XueshengkongbaiyemianView(XueshengkongbaiyemianEntity xueshengkongbaiyemianEntity){
 	try {
			BeanUtils.copyProperties(this, xueshengkongbaiyemianEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}



}
