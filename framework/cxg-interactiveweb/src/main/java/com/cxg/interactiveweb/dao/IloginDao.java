package com.cxg.interactiveweb.dao;

import java.util.List;
import java.util.Map;

import com.cxg.interactiveweb.pojo.Loginpo;



/**
 * @ClassName: UserDaoInterface
* @Description: UserDao的接口类，也属于爱易CRUD的插件扩展类。这个类一般情况下不用创建。当需要重载或者新增REUD框架的一些方法时，可以声明接口类
 * 本接口类重载了add()方法，使add方法可以一次性插入多条数据，极大的提高了数据批量增加的效率
* @author haipeng
* @date 2017年1月8日
 */
public interface IloginDao {

	int add(List<Loginpo> list);
	
	List<Map> queryuserAndImg();
}
