/**
 * File：StringToDateConverter.java
 * Author：jin
 * Date：2017年10月31日 下午6:28:22
 */
package com.fang.passport.converter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * <p>
 * Description: StringToDateConverter
 * </p>
 *
 * @author jinshilei
 *         2017年10月31日
 * @version 1.0
 *
 */
@Component
public class StringToDateConverter implements Converter<String, Date> {

  private SimpleDateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd");

  @Override
  public Date convert(String source) {
    try {
      return dateFormate.parse(source);
    } catch (Exception e) {
    }
    return null;
  }

}
