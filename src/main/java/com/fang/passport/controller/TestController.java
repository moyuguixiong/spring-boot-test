/**
 * File：TestController.java
 * Author：jin
 * Date：2017年10月31日 下午3:54:45
 */
package com.fang.passport.controller;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fang.passport.service.GetUserService;
import com.fang.passport.vo.TestInVo;

/**
 * <p>
 * Description: TestController
 * </p>
 *
 * @author jinshilei
 *         2017年10月31日
 * @version 1.0
 *
 */
@RestController
public class TestController {

  @Value(value = "${passport.security}")
  private String uuid;

  @Value(value = "${passport.init}")
  private Integer init;

  @Autowired
  private Environment environment;

  @Autowired
  private GetUserService getUserService;

  @Autowired
  @Qualifier("dataSourceR")
  private DataSource dataSourceR;

  @Autowired
  @Qualifier("dataSourceW")
  private DataSource dataSourceW;

  @RequestMapping(value = "/test.aspx", produces = {"text/json;charset=utf-8"})
  public String test(TestInVo inVo) throws SQLException {
    System.out.println(inVo);
    System.out.println(uuid);
    System.out.println(init);
    System.out.println(environment.getProperty("server.port"));
    System.out.println(getUserService.getUserNameById(10));
    System.out.println(dataSourceR.getConnection().getMetaData().getUserName());
    System.out.println(dataSourceW.getConnection().getMetaData().getUserName());
    return "靳石磊";
  }
}
