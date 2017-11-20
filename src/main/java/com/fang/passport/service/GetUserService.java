/**
 * File：GetUserService.java
 * Author：jin
 * Date：2017年11月8日 下午4:09:37
 */
package com.fang.passport.service;

import com.fang.passport.dao.GetUserDao;

/**
 * <p>
 * Description: GetUserService
 * </p>
 *
 * @author jinshilei
 *         2017年11月8日
 * @version 1.0
 *
 */
public class GetUserService {

  private GetUserDao getUserDao;

  public void setGetUserDao(GetUserDao getUserDao) {
    this.getUserDao = getUserDao;
  }

  public String getUserNameById(int userId) {
    return getUserDao.getUserNameById(userId);
  }
}
