/**
 * File：MyDbStrategy.java
 * Author：jin
 * Date：2017年12月12日 下午6:13:05
 */
package com.fang.passport.config;

import java.util.Collection;

import io.shardingjdbc.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

/**
 * <p>
 * Description: MyDbStrategy
 * </p>
 *
 * @author jinshilei
 *         2017年12月12日
 * @version 1.0
 *
 */
public class MyDbStrategy implements PreciseShardingAlgorithm<Long> {

  @Override
  public String doSharding(Collection<String> availableTargetNames,
                           PreciseShardingValue<Long> shardingValue) {

    // System.out.println(shardingValue.getLogicTableName());
    // System.out.println(shardingValue.getColumnName());
    // System.out.println(shardingValue.getValue());
    long value = shardingValue.getValue();
    if (value > 10) {
      value = value / 10;
    }
    for (String tableName : availableTargetNames) {
      if (tableName.equals(shardingValue.getLogicTableName() + "_" + (value % 2))) {
        return tableName;
      }
    }
    throw new IllegalArgumentException();
  }

}
