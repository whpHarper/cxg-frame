package com.cxg.core.beans;

import java.io.Serializable;

import com.cxg.core.sql.where.Constant;

public class Method {

	/**
	 * where重写
	 * @param pram
	 * @return
	 */
	public static WherePrams where(String file, String where, Serializable value){
			return new WherePrams(file , where , value);
	}
	public static WherePrams where(String file, Constant c, Serializable value){
		String where = Constant.getSqlWhere(c);
		return where(file, where, value);
	}
	public static WherePrams createDefault(){
		return new WherePrams(null, null, null);
	}
	
}
