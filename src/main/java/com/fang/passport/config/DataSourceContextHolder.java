/**
 * File：DataSourceContextHolder.java
 * Author：jin
 * Date：2017年11月20日 下午4:58:46
 */
package com.fang.passport.config;

/**
 * <p>
 * Description: DataSourceContextHolder
 * </p>
 *
 * @author jinshilei
 *         2017年11月20日
 * @version 1.0
 *
 */
public class DataSourceContextHolder {

  public static final ThreadLocal<String> DATASOURCEHOLDER = new ThreadLocal<String>();

  public static String getDataSource() {
    return DATASOURCEHOLDER.get();
  }

  public static void setDataSource(String dataSourceName) {
    DATASOURCEHOLDER.set(dataSourceName);
  }

  public static void removeDataSource() {
    DATASOURCEHOLDER.remove();
  }
}
