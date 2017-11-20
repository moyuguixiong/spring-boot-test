/**
 * File：AppConfig.java
 * Author：jin
 * Date：2017年11月8日 下午4:10:06
 */
package com.fang.passport.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.fang.passport.dao.GetUserDao;
import com.fang.passport.service.GetUserService;

/**
 * <p>
 * Description: AppConfig
 * </p>
 *
 * @author jinshilei
 *         2017年11月8日
 * @version 1.0
 *
 */
@Configuration
public class AppConfig {

  @Bean
  @Qualifier("userdao")
  // @Scope("prototype")
  // 默认是单例的
  @Scope("singleton")
  public GetUserDao getGetUserDao() {
    GetUserDao dao = new GetUserDao();
    System.out.println(dao.toString());
    return dao;
  }

  @Bean
  public GetUserService getGetUserService(@Qualifier("userdao") GetUserDao getUserDao) {
    GetUserService getUserService = new GetUserService();
    System.out.println(getUserDao.toString());
    getUserService.setGetUserDao(getUserDao);
    return getUserService;
  }
}
