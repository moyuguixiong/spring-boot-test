package com.fang.passport.dao;

import com.fang.passport.po.SvcOrderItem;
import com.fang.passport.po.SvcOrderItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SvcOrderItemMapper {
    long countByExample(SvcOrderItemExample example);

    int deleteByExample(SvcOrderItemExample example);

    int deleteByPrimaryKey(Long orderItemId);

    int insert(SvcOrderItem record);

    int insertSelective(SvcOrderItem record);

    List<SvcOrderItem> selectByExample(SvcOrderItemExample example);

    SvcOrderItem selectByPrimaryKey(Long orderItemId);

    int updateByExampleSelective(@Param("record") SvcOrderItem record, @Param("example") SvcOrderItemExample example);

    int updateByExample(@Param("record") SvcOrderItem record, @Param("example") SvcOrderItemExample example);

    int updateByPrimaryKeySelective(SvcOrderItem record);

    int updateByPrimaryKey(SvcOrderItem record);
}