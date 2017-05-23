package com.cxg.core.aop;

import javax.annotation.Resource;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @ClassName: TransactionManager
* @Description: 内部自动化事物控制器
* @author haipeng
* @date 2017年1月3日
 */
@Aspect
public class TransactionManager {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Pointcut("@annotation(com.cxg.core.annotation.po.TraMethod)")
	public void transactionMethod(){}
	
	@Before("transactionMethod()")
	public void openTra(){
		System.out.println("前置^^^事务开启");
	}
	
	@AfterReturning("transactionMethod()")
	public void cmmintTra(){
		System.out.println("前置^^^事务提交");
	}
}
