/**
 * File：MybatisConfig.java
 * Author：jin
 * Date：2017年11月8日 下午6:36:42
 */
package com.fang.passport.config;

import io.shardingjdbc.core.api.ShardingDataSourceFactory;
import io.shardingjdbc.core.api.config.MasterSlaveRuleConfiguration;
import io.shardingjdbc.core.api.config.ShardingRuleConfiguration;
import io.shardingjdbc.core.api.config.TableRuleConfiguration;
import io.shardingjdbc.core.api.config.strategy.InlineShardingStrategyConfiguration;
import io.shardingjdbc.core.api.config.strategy.StandardShardingStrategyConfiguration;
import io.shardingjdbc.core.constant.ShardingPropertiesConstant;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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
@EnableTransactionManagement
public class MybatisConfig {

  // @Bean
  // public DynamicDataSource getDataSource(@Qualifier("dataSourceR") DataSource dataSourceR,
  // @Qualifier("dataSourceW") DataSource dataSourceW) {
  // DynamicDataSource dataSource = new DynamicDataSource();
  // dataSource.setDefaultTargetDataSource(dataSourceR);
  // Map<Object, Object> map = new HashMap<Object, Object>();
  // map.put("dataSourceR", dataSourceR);
  // map.put("dataSourceW", dataSourceW);
  // dataSource.setTargetDataSources(map);
  // return dataSource;
  // }

  @Bean(name = "shardingDataSource")
  public DataSource buildDataSource() throws SQLException {
    // 构建读写分离数据源, 读写分离数据源实现了DataSource接口, 可直接当做数据源处理. masterDataSource0, slaveDataSource00,
    // slaveDataSource01等为使用DBCP等连接池配置的真实数据源
    Map<String, DataSource> dataSourceMap = new HashMap<>();
    dataSourceMap.put("database_0_write", createDataSource("database_0", false));
    dataSourceMap.put("database_0_read", createDataSource("database_0", true));
    dataSourceMap.put("database_1_write", createDataSource("database_1", false));
    dataSourceMap.put("database_1_read", createDataSource("database_1", true));

    // dataSourceMap.put("database_0", createDataSource("database_0", false));
    // dataSourceMap.put("database_1", createDataSource("database_1", false));

    // 构建读写分离配置
    MasterSlaveRuleConfiguration masterSlaveRuleConfig0 = new MasterSlaveRuleConfiguration();
    masterSlaveRuleConfig0.setName("database_0");
    masterSlaveRuleConfig0.setMasterDataSourceName("database_0_write");
    masterSlaveRuleConfig0.getSlaveDataSourceNames().add("database_0_read");

    MasterSlaveRuleConfiguration masterSlaveRuleConfig1 = new MasterSlaveRuleConfiguration();
    masterSlaveRuleConfig1.setName("database_1");
    masterSlaveRuleConfig1.setMasterDataSourceName("database_1_write");
    masterSlaveRuleConfig1.getSlaveDataSourceNames().add("database_1_read");

    // 通过ShardingSlaveDataSourceFactory继续创建ShardingDataSource
    ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
    shardingRuleConfig.setDefaultDataSourceName("database_0");
    shardingRuleConfig.getMasterSlaveRuleConfigs().add(masterSlaveRuleConfig0);
    shardingRuleConfig.getMasterSlaveRuleConfigs().add(masterSlaveRuleConfig1);

    // 分表策略
    shardingRuleConfig.getTableRuleConfigs().add(getOrderTableRuleConfiguration());
    shardingRuleConfig.getTableRuleConfigs().add(getOrderItemTableRuleConfiguration());

    Properties properties = new Properties();
    properties.setProperty(ShardingPropertiesConstant.SQL_SHOW.getKey(), "true");
    DataSource dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap,
        shardingRuleConfig, new ConcurrentHashMap<String, Object>(), properties);
    return dataSource;
  }

  static TableRuleConfiguration getOrderTableRuleConfiguration() {
    TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration();
    orderTableRuleConfig.setLogicTable("svc_order");
    orderTableRuleConfig.setActualDataNodes("database_${0..1}.svc_order_${0..1}");
    orderTableRuleConfig.setKeyGeneratorColumnName("order_id");
    orderTableRuleConfig.setKeyGeneratorClass("io.shardingjdbc.core.keygen.DefaultKeyGenerator");
    orderTableRuleConfig.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration(
        "order_id", "database_${order_id % 2}"));
    orderTableRuleConfig.setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration(
        "order_id", "com.fang.passport.config.MyDbStrategy"));
    return orderTableRuleConfig;
  }

  static TableRuleConfiguration getOrderItemTableRuleConfiguration() {
    TableRuleConfiguration orderItemTableRuleConfig = new TableRuleConfiguration();
    orderItemTableRuleConfig.setLogicTable("svc_order_item");
    orderItemTableRuleConfig.setActualDataNodes("database_${0..1}.svc_order_item_${0..1}");
    orderItemTableRuleConfig
        .setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("order_item_id",
            "database_${order_item_id % 2}"));
    orderItemTableRuleConfig
        .setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("order_id",
            "com.fang.passport.config.MyDbStrategy"));
    return orderItemTableRuleConfig;
  }

  private static DataSource createDataSource(final String dataSourceName, boolean isRead) {
    org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();

    String url = String.format(
        "jdbc:mysql://127.0.0.1:3306/%s?useUnicode=true&characterEncoding=utf-8&useSSL=false",
        dataSourceName);
    System.out.println(url);
    dataSource.setUrl(url);
    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    if (isRead) {
      dataSource.setUsername("test2_r");
    } else {
      dataSource.setUsername("root");
    }
    dataSource.setPassword("123456");
    return dataSource;
  }

  @Bean
  public DataSourceTransactionManager transactionManager(@Qualifier("shardingDataSource") DataSource dataSource)
      throws Exception {
    return new DataSourceTransactionManager(dataSource);
  }

  @Bean(name = "sqlSessionFactory")
  public SqlSessionFactory getSqlSessionFactory(@Qualifier("shardingDataSource") DataSource dataSource)
      throws Exception {
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
