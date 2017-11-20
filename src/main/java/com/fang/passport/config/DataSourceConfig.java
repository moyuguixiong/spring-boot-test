/**
 * File：DataSourceConfig.java
 * Author：jin
 * Date：2017年11月8日 下午5:52:07
 */
package com.fang.passport.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

/**
 * <p>
 * Description: DataSourceConfig
 * </p>
 *
 * @author jinshilei
 *         2017年11月8日
 * @version 1.0
 *
 */
@Configuration
public class DataSourceConfig {

  @Autowired
  private Environment env;

  @Bean
  @Primary
  @Qualifier("dataSourceR")
  public DataSource getReadDataSource() {
    DataSource readDataSource = new DataSource();
    readDataSource.setDriverClassName(env.getProperty("jdbc.driverClass"));
    readDataSource.setUrl(env.getProperty("jdbc.url"));
    readDataSource.setUsername(env.getProperty("jdbc.read.username"));
    readDataSource.setPassword(env.getProperty("jdbc.read.password"));
    return readDataSource;
  }

  @Bean
  @Qualifier("dataSourceW")
  public DataSource getWriteDataSource() {
    DataSource writeDataSource = new DataSource();
    writeDataSource.setDriverClassName(env.getProperty("jdbc.driverClass"));
    writeDataSource.setUrl(env.getProperty("jdbc.url"));
    writeDataSource.setUsername(env.getProperty("jdbc.write.username"));
    writeDataSource.setPassword(env.getProperty("jdbc.write.password"));
    return writeDataSource;
  }
}
