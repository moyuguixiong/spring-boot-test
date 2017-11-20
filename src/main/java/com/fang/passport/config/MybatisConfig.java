/**
 * File：MybatisConfig.java
 * Author：jin
 * Date：2017年11月8日 下午6:36:42
 */
package com.fang.passport.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * <p>
 * Description: MybatisConfig
 * </p>
 *
 * @author jinshilei
 *         2017年11月8日
 * @version 1.0
 *
 */
@Configuration
// @EnableTransactionManagement
public class MybatisConfig {

  @Bean
  public DynamicDataSource getDataSource(@Qualifier("dataSourceR") DataSource dataSourceR,
                                         @Qualifier("dataSourceW") DataSource dataSourceW) {
    DynamicDataSource dataSource = new DynamicDataSource();
    dataSource.setDefaultTargetDataSource(dataSourceR);
    Map<Object, Object> map = new HashMap<Object, Object>();
    map.put("dataSourceR", dataSourceR);
    map.put("dataSourceW", dataSourceW);
    dataSource.setTargetDataSources(map);
    return dataSource;
  }

  @Bean
  @Qualifier("sqlSessionFactory")
  public SqlSessionFactory getSqlSessionFactory(DynamicDataSource dataSource) throws Exception {

    // 设置数据源
    SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
    bean.setDataSource(dataSource);
    // 设置MapperXml的扫描路径
    PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    Resource[] resources = resolver.getResources("classpath:com/fang/passport/dao/*Mapper.xml");
    bean.setMapperLocations(resources);
    return bean.getObject();
  }
}
