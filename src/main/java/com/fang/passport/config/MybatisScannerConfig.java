/**
 * File：MybatisScannerConfig.java
 * Author：jin
 * Date：2017年11月8日 下午7:22:40
 */
package com.fang.passport.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * Description: MybatisScannerConfig
 * </p>
 *
 * @author jinshilei
 *         2017年11月8日
 * @version 1.0
 *
 */
@Configuration
// 在类MybatisConfig中的SqlSessionFactory对象生成后再去扫描，否则会报错
@AutoConfigureAfter(value = {MybatisConfig.class})
public class MybatisScannerConfig {

  @Bean
  public MapperScannerConfigurer getMapperScannerConfigurer() {
    MapperScannerConfigurer scanner = new MapperScannerConfigurer();
    // 设置SqlSessionFactory工厂对象
    scanner.setSqlSessionFactoryBeanName("sqlSessionFactory");
    // 设置Mapper接口扫描路径
    scanner.setBasePackage("com.fang.passport.dao");
    return scanner;
  }
}
