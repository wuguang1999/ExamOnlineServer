package com.volcano.examonlineserv.mapper;

import com.volcano.examonlineserv.bean.QuestionInfo;
import com.volcano.examonlineserv.bean.QuestionInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface QuestionInfoMapper {
    long countByExample(QuestionInfoExample example);

    int deleteByExample(QuestionInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(QuestionInfo record);

    int insertSelective(QuestionInfo record);

    List<QuestionInfo> selectByExample(QuestionInfoExample example);

    QuestionInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") QuestionInfo record, @Param("example") QuestionInfoExample example);

    int updateByExample(@Param("record") QuestionInfo record, @Param("example") QuestionInfoExample example);

    int updateByPrimaryKeySelective(QuestionInfo record);

    int updateByPrimaryKey(QuestionInfo record);

    @Select("SELECT COUNT(id) FROM questioninfo")
    int countQuestions();

    @Select("SELECT * FROM questioninfo ORDER BY RAND() LIMIT #{num}")
    List<QuestionInfo> getRandomQuestions(Integer num);
}