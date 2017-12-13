package com.fang.passport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fang.passport.po.SvcOrder;
import com.fang.passport.service.SvcOrderService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional(transactionManager = "transactionManager")
@Rollback
public class SpringBootTestApplicationTests {

  @Autowired
  private SvcOrderService svcOrderService;

  @Test
  @Rollback(false)
  public void contextLoads() {
    for (int i = 0; i < 100; i++) {
      SvcOrder order = new SvcOrder();
      // order.setOrderId((long) (i + 1));
      order.setOrderDesc("test");
      svcOrderService.add(order);
    }
    // SvcOrder svcOrder = svcOrderService.getById(147697479394000896L);
    // if (svcOrder != null) {
    // System.out.println(svcOrder.getOrderId() + ":" + svcOrder.getOrderDesc());
    // }
  }

  @Test
  public void testForJackson() {
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader("C:\\Users\\jin\\Desktop\\dbcfg.conf"));
      String str = null;
      StringBuilder sBuilder = new StringBuilder();
      while ((str = reader.readLine()) != null) {
        sBuilder.append(str);
      }
      String result = sBuilder.toString().replaceAll(" ", "")
          .replaceAll("(\r\n|\r|\n|\n\r|\t)", "");
      // System.out.println(result);
      ObjectMapper json = new ObjectMapper();
      Object obj = json.readValue(result, Object.class);
      System.out.println(obj);

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  @Test
  public void testForJson() {

    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader("C:\\Users\\jin\\Desktop\\dbcfg.conf"));
      String str = null;
      StringBuilder sBuilder = new StringBuilder();
      while ((str = reader.readLine()) != null) {
        sBuilder.append(str);
      }
      String result = sBuilder.toString().replaceAll(" ", "")
          .replaceAll("(\r\n|\r|\n|\n\r|\t)", "");
      // System.out.println(result);
      Object obj = JSON.parse(result);
      if (obj instanceof JSONObject) {
        JSONObject jsonObj = (JSONObject) obj;
        for (String key : jsonObj.keySet()) {
          System.out.println(key);
          JSONObject jsonObj2 = (JSONObject) jsonObj.get(key);
          for (String key2 : jsonObj2.keySet()) {
            System.out.println(key2 + ":" + jsonObj2.get(key2));
          }
          System.out.println();
        }
      }
      System.out.println(obj);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

  }
}
