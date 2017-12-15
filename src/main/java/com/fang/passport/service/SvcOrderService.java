/**
 * File：SvcOrderService.java
 * Author：jin
 * Date：2017年12月11日 下午8:38:31
 */
package com.fang.passport.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fang.passport.dao.SvcOrderMapper;
import com.fang.passport.po.SvcOrder;
import com.fang.passport.po.SvcOrderExample;

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

  public List<SvcOrder> getByDesc(String desc) {
    SvcOrderExample example = new SvcOrderExample();
    SvcOrderExample.Criteria criteria = example.createCriteria();
    criteria.andOrderDescEqualTo(desc);
    List<SvcOrder> list = svcOrderMapper.selectByExample(example);
    return list;
  }

  public void add(SvcOrder svcOrder) {
    svcOrderMapper.insertSelective(svcOrder);
  }

  public List<SvcOrder> getByIds(List<Long> orderIds) {
    SvcOrderExample example = new SvcOrderExample();
    SvcOrderExample.Criteria criteria = example.createCriteria();
    criteria.andOrderIdIn(orderIds);
    List<SvcOrder> list = svcOrderMapper.selectByExample(example);
    return list;
  }

  @Transactional
  public void addBatch(List<SvcOrder> list) {
    if (list != null && list.size() > 0) {
      for (SvcOrder svcOrder : list) {
        svcOrderMapper.insertSelective(svcOrder);
      }
    }
  }
}
