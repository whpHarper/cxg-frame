package com.cxg.core.util;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;








import com.cxg.core.annotation.po.FieldName;
import com.cxg.core.annotation.po.TableName;
import com.cxg.core.annotation.po.TempField;
import com.cxg.core.beans.Constraint;
import com.cxg.core.beans.Parameter;
import com.cxg.core.sql.exception.AiyiIdTypeException;

public class SqlUtil {
	
//	/**
//	 * 链接
//	 */
//	public static final QueryRunner SQLSESSION = new QueryRunner((DataSource)new ClassPathXmlApplicationContext("spring.xml").getBean("dataSource"));
	
	
	/**
	 * 获取一个实体类中的所有字段
	 * @param po
	 * @return
	 */
	public static List<Parameter> getPramList(Constraint po){
		List<Parameter> list = new ArrayList<>();
		Class<? extends Constraint> thisClass = po.getClass();
	    Field[] fields = thisClass.getDeclaredFields();
	    for(Field f : fields){
	    	try {
	    		if(!f.getName().equalsIgnoreCase("ID") && !f.isAnnotationPresent(TempField.class)){
	    			String fName = f.getName();
	    			if (f.isAnnotationPresent(FieldName.class)) {
	    				String fieldName = f.getAnnotation(FieldName.class).name();
						Parameter pram = new Parameter(fieldName, thisClass.getMethod("get" + fName.substring(0, 1).toUpperCase() + fName.substring(1)).invoke(po));
						list.add(pram);
	    			}else{
	    				String fieldName = toTableString(fName);
	    				Parameter pram = new Parameter(fieldName, thisClass.getMethod("get" + fName.substring(0, 1).toUpperCase() + fName.substring(1)).invoke(po));
						list.add(pram);
					}
	    		}
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	    }
		return list;
	}
	
	/**
	 * 获取一个实体类中所有字段的查询语句
	 * @param po
	 * @return
	 */
	public static List<Parameter> getPramListOfSelect(Constraint po){
		List<Parameter> list = new ArrayList<>();
		Class<? extends Constraint> thisClass = po.getClass();
	    Field[] fields = thisClass.getDeclaredFields();
	    for(Field f : fields){
	    	try {
	    		if (!f.isAnnotationPresent(TempField.class)) {
	    			String fName = f.getName();
	    			if (f.isAnnotationPresent(FieldName.class)) {
	    				String fieldName = f.getAnnotation(FieldName.class).name();
						Parameter pram = new Parameter(fieldName + " as " + fName, thisClass.getMethod("get" + fName.substring(0, 1).toUpperCase() + fName.substring(1)).invoke(po));
						list.add(pram);
	    			}else{
	    				String fieldName = toTableString(fName);
	    				Parameter pram = new Parameter(fieldName + " as " + fName, thisClass.getMethod("get" + fName.substring(0, 1).toUpperCase() + fName.substring(1)).invoke(po));
						list.add(pram);
					}
				}
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	    }
		return list;
	}
	
	/**
	 * 获取一个实体类对应的数据库表名
	 * @param po
	 * @return
	 */
	public static String getTableName(Constraint po){
		Class<? extends Constraint> c = po.getClass();
		if(c.isAnnotationPresent(TableName.class)){
			return c.getAnnotation(TableName.class).name();
		}else{
			String className = po.getClass().getSimpleName();
			String tName = toTableString(className);
			String poName = tName.substring(tName.length() - 2, tName.length());
			if("po".equals(poName)){
				tName = tName.substring(0,tName.length() - 3);
			}
			return tName;
		}
		
	}
	
	/**
	 * 获取一个实体类中的所有字段
	 * @param po
	 * @return
	 */
	public static<T extends Constraint> List<Parameter> getPramList(Class<T> po){
		List<Parameter> list = new ArrayList<>();
		Class<? extends Constraint> thisClass = po;
	    Field[] fields = thisClass.getDeclaredFields();
	    	try {
	    		Object o = thisClass.newInstance();
	    		for(Field f : fields){
		    		if(!f.getName().equalsIgnoreCase("ID") && !f.isAnnotationPresent(TempField.class)){
		    			String fName = f.getName();
		    			if (f.isAnnotationPresent(FieldName.class)) {
		    				String fieldName = f.getAnnotation(FieldName.class).name();
		    				Method get = thisClass.getMethod("get" + fName.substring(0, 1).toUpperCase() + fName.substring(1));
		    				Object getValue = get.invoke(o);
		    				Parameter pram = new Parameter(fieldName, getValue);
							list.add(pram);
		    			}else{
		    				String fieldName = toTableString(fName);
		    				Method get = thisClass.getMethod("get" + fName.substring(0, 1).toUpperCase() + fName.substring(1));
		    				Object getValue = get.invoke(o);
		    				Parameter pram = new Parameter(fieldName + fName, getValue);
							list.add(pram);
						}
		    		}
	    		}
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return list;
	}
	
	
	/**
	 * 获取一个实体类中的所有字段的查询语句
	 * @param po
	 * @return
	 */
	public static<T extends Constraint> List<Parameter> getPramListOfSelect(Class<T> po){
		List<Parameter> list = new ArrayList<>();
		Class<? extends Constraint> thisClass = po;
	    Field[] fields = thisClass.getDeclaredFields();
	    	try {
	    		Object o = thisClass.newInstance();
	    		for(Field f : fields){
	    			if (!f.isAnnotationPresent(TempField.class)) {
	    				String fName = f.getName();
		    			if (f.isAnnotationPresent(FieldName.class)) {
		    				String fieldName = f.getAnnotation(FieldName.class).name();
		    				Method get = thisClass.getMethod("get" + fName.substring(0, 1).toUpperCase() + fName.substring(1));
		    				Object getValue = get.invoke(o);
		    				Parameter pram = new Parameter(fieldName + " as " + fName, getValue);
							list.add(pram);
		    			}else{
		    				String fieldName = toTableString(fName);
		    				Method get = thisClass.getMethod("get" + fName.substring(0, 1).toUpperCase() + fName.substring(1));
		    				Object getValue = get.invoke(o);
		    				Parameter pram = new Parameter(fieldName + " as " + fName, getValue);
							list.add(pram);
						}
					}
	    		}
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return list;
	}
	
	/**
	 * 获取一个JavaBean中的所有字段
	 * @param po
	 * @return
	 */
	public static<T> List<Parameter> getPramListByBean(Class<T> po){
		List<Parameter> list = new ArrayList<>();
		Class<?> thisClass = po;
	    Field[] fields = thisClass.getDeclaredFields();
	    try {
    		Object o = thisClass.newInstance();
    		for(Field f : fields){
	    		if(!f.getName().equalsIgnoreCase("ID") && !f.isAnnotationPresent(TempField.class)){
	    			
	    			String fName = f.getName();
	    			if (f.isAnnotationPresent(FieldName.class)) {
	    				String fieldName = f.getAnnotation(FieldName.class).name();
	    				Method get = thisClass.getMethod("get" + fName.substring(0, 1).toUpperCase() + fName.substring(1));
	    				Object getValue = get.invoke(o);
	    				Parameter pram = new Parameter(fieldName + " as " + fName, getValue);
						list.add(pram);
	    			}else{
	    				String fieldName = toTableString(fName);
	    				Method get = thisClass.getMethod("get" + fName.substring(0, 1).toUpperCase() + fName.substring(1));
	    				Object getValue = get.invoke(o);
	    				Parameter pram = new Parameter(fieldName + " as " + fName, getValue);
						list.add(pram);
					}
	    			
	    		}
    		}
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 通过一个符合实体类标准的JavaBean来查询数据库
	 * @param po
	 * @return
	 */
	public static<T> List<Parameter> getPramListByBeanOfSelect(Class<T> po){
		List<Parameter> list = new ArrayList<>();
		Class<?> thisClass = po;
	    Field[] fields = thisClass.getDeclaredFields();
	    try {
    		Object o = thisClass.newInstance();
    		for(Field f : fields){
    			if (!f.isAnnotationPresent(TempField.class)) {
    				String fName = f.getName();
	    			if (f.isAnnotationPresent(FieldName.class)) {
	    				String fieldName = f.getAnnotation(FieldName.class).name();
	    				Method get = thisClass.getMethod("get" + fName.substring(0, 1).toUpperCase() + fName.substring(1));
	    				Object getValue = get.invoke(o);
	    				Parameter pram = new Parameter(fieldName + " as " + fName, getValue);
						list.add(pram);
	    			}else{
	    				String fieldName = toTableString(fName);
	    				Method get = thisClass.getMethod("get" + fName.substring(0, 1).toUpperCase() + fName.substring(1));
	    				Object getValue = get.invoke(o);
	    				Parameter pram = new Parameter(fieldName + " as " + fName, getValue);
						list.add(pram);
					}
				}
    		}
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 取一个实体类类对应的数据库表名
	 * @param po
	 * @return
	 */
	public static<T extends Constraint> String getTableName(Class<T> po){
		if(po.isAnnotationPresent(TableName.class)){
			return po.getAnnotation(TableName.class).name();
		}else{
			String tName = toTableString(po.getSimpleName());
			String poName = tName.substring(tName.length() - 2, tName.length());
			if("po".equals(poName)){
				tName = tName.substring(0,tName.length() - 3);
			}
			return tName;
		}
	}
	
	/**
	 * 取一个类对应的数据库表名
	 * 或将一个JavaBean中的类名转换为数据库模式
	 * @param po
	 * @return
	 */
	public static<T> String getTableNameByBean(Class<T> po){
		if(po.isAnnotationPresent(TableName.class)){
			return po.getAnnotation(TableName.class).name();
		}else{
			String tName = toTableString(po.getSimpleName());
			if("po".equals(tName.substring(tName.length() - 3, tName.length() - 1))){
				tName = tName.substring(0,tName.length() - 3);
			}
			return tName;
		}
	}
	
	/**
	 * 获取实体类中的某个值
	 * @param po
	 * @param fileName
	 * @return
	 */
	public static<T> Serializable getFileValue(Class<T> po, String fileName){
		try {
			Method method = po.getMethod("get" + fileName.substring(0, 1).toUpperCase() + fileName.substring(1));
			Object o = po.newInstance();
			Object invoke = method.invoke(o);
			return null == invoke ? null : (Serializable)invoke;
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 取字段名
	 * @param po
	 * @param fileName
	 * @return
	 */
	public static<T> Serializable getFileValue(Constraint po, String fileName){
		try {
			Class<? extends Constraint> cla = po.getClass();
			Method method = cla.getMethod("get" + fileName.substring(0, 1).toUpperCase() + fileName.substring(1));
			Object o = po;
			Object invoke = method.invoke(o);
			return null == invoke ? null : (Serializable)invoke;
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	
	
	/**
	 * 将某个值通过反射强制赋给实体类
	 * @param po
	 * @param fileName
	 * @param fileValue
	 * @return
	 */
	public static boolean setFileValue(Constraint po, String fileName, Serializable fileValue){
		Class<? extends Constraint> thisClass = po.getClass();
		try {
			if ("ID".equalsIgnoreCase(fileName)) {
				try {
					Field field = thisClass.getDeclaredField(fileName);
					String calssName = field.getType().getName();
					if (calssName.equals("int") || calssName.equals("java.lang.Integer")) {
						if (Integer.MAX_VALUE >  new Integer("" + fileValue)) {
							Integer val = new Integer("" + fileValue);
							Method method = thisClass.getMethod("set" + fileName.substring(0, 1).toUpperCase() + fileName.substring(1), field.getType());
							method.invoke(po, val);
							return true;
						}else{
							throw new AiyiIdTypeException("ID type is not a corresponding type at " + "set" + fileName.substring(0, 1).toUpperCase() + fileName.substring(1) + "\n"
									+ "the will give value type is " + fileValue.getClass().getName() + "\n" 
									+ "the filed type type is " + field.getType().getName());
						}
					}else if(calssName.equals("long") || calssName.equals("java.lang.Long")){
						Long val = new Long("" + fileValue);
						Method method = thisClass.getMethod("set" + fileName.substring(0, 1).toUpperCase() + fileName.substring(1), field.getType());
						method.invoke(po, val);
						return true;
					}else{
						Method method = thisClass.getMethod("set" + fileName.substring(0, 1).toUpperCase() + fileName.substring(1), field.getType());
						method.invoke(po, fileValue);
						return true;
					}
				} catch (AiyiIdTypeException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NoSuchFieldException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Method method = thisClass.getMethod("set" + fileName.substring(0, 1).toUpperCase() + fileName.substring(1), fileValue.getClass());
			method.invoke(po, fileValue);
			return true;
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
	/**
	 * 将驼峰标识转换为下划线
	 * @param text
	 * @return
	 */
	public static String toTableString(String text){
		String tName = text.substring(0, 1).toLowerCase();
		for(int i = 1; i < text.length(); i++){
			if(!Character.isLowerCase(text.charAt(i))){
				tName += "_";
			}
			tName += text.substring(i, i + 1);
		}
		return tName.toLowerCase();
	}
	
	
}
