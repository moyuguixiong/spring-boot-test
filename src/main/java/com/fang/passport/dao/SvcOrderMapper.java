package com.fang.passport.dao;

import com.fang.passport.po.SvcOrder;
import com.fang.passport.po.SvcOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SvcOrderMapper {
    long countByExample(SvcOrderExample example);

    int deleteByExample(SvcOrderExample example);

    int deleteByPrimaryKey(Long orderId);

    int insert(SvcOrder record);

    int insertSelective(SvcOrder record);

    List<SvcOrder> selectByExample(SvcOrderExample example);

    SvcOrder selectByPrimaryKey(Long orderId);

    int updateByExampleSelective(@Param("record") SvcOrder record, @Param("example") SvcOrderExample example);

    int updateByExample(@Param("record") SvcOrder record, @Param("example") SvcOrderExample example);

    int updateByPrimaryKeySelective(SvcOrder record);

    int updateByPrimaryKey(SvcOrder record);
}