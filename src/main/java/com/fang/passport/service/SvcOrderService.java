/**
 * File：SvcOrderService.java
 * Author：jin
 * Date：2017年12月11日 下午8:38:31
 */
package com.fang.passport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fang.passport.dao.SvcOrderMapper;
import com.fang.passport.po.SvcOrder;

/**
 * <p>
 * Description: SvcOrderService
 * </p>
 *
 * @author jinshilei
 *         2017年12月11日
 * @version 1.0
 *
 */
@Service
public class SvcOrderService {

  @Autowired
  private SvcOrderMapper svcOrderMapper;

  public SvcOrder getById(long orderId) {
    SvcOrder order = svcOrderMapper.selectByPrimaryKey(orderId);
    return order;
  }

  public void add(SvcOrder svcOrder) {
    svcOrderMapper.insertSelective(svcOrder);
  }
}
