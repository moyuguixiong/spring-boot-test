/**
 * File：DynamicDataSource.java
 * Author：jin
 * Date：2017年11月20日 下午4:55:48
 */
package com.fang.passport.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * <p>
 * Description: DynamicDataSource
 * </p>
 *
 * @author jinshilei
 *         2017年11月20日
 * @version 1.0
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

  @Override
  protected Object determineCurrentLookupKey() {
    return DataSourceContextHolder.getDataSource();
  }
}
