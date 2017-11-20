/**
 * File：TestInVo.java
 * Author：jin
 * Date：2017年10月31日 下午5:25:38
 */
package com.fang.passport.vo;

import java.util.Date;

/**
 * <p>
 * Description: TestInVo
 * </p>
 *
 * @author jinshilei
 *         2017年10月31日
 * @version 1.0
 *
 */
public class TestInVo {

  private String name;

  private Integer age;

  private Date birthday;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

}
