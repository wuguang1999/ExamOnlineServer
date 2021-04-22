package com.volcano.examonlineserv.mapper;

import com.volcano.examonlineserv.bean.HotKey;
import com.volcano.examonlineserv.bean.HotKeyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HotKeyMapper {
    long countByExample(HotKeyExample example);

    int deleteByExample(HotKeyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(HotKey record);

    int insertSelective(HotKey record);

    List<HotKey> selectByExample(HotKeyExample example);

    HotKey selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") HotKey record, @Param("example") HotKeyExample example);

    int updateByExample(@Param("record") HotKey record, @Param("example") HotKeyExample example);

    int updateByPrimaryKeySelective(HotKey record);

    int updateByPrimaryKey(HotKey record);
}