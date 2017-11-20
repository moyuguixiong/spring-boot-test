/**
 * File：TransactionAspectj.java
 * Author：jin
 * Date：2017年11月20日 下午5:55:08
 */
package com.fang.passport.ascpectj;

import org.apache.logging.log4j.core.config.Order;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.fang.passport.config.DataSourceContextHolder;

/**
 * <p>
 * Description: TransactionAspectj
 * </p>
 *
 * @author jinshilei
 *         2017年11月20日
 * @version 1.0
 *
 */
@Component
@Aspect
@Order(-1)
public class TransactionAspectj {

  @Before(value = "@annotation(org.springframework.transaction.annotation.Transactional)")
  public void before(JoinPoint point) {
    DataSourceContextHolder.setDataSource("dataSourceW");
  }

  @After(value = "@annotation(org.springframework.transaction.annotation.Transactional)")
  public void after(JoinPoint point) {
    DataSourceContextHolder.removeDataSource();
  }
}
