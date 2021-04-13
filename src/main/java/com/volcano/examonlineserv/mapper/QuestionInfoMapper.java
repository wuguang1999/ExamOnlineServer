package com.volcano.examonlineserv.mapper;

import com.volcano.examonlineserv.bean.QuestionInfo;
import com.volcano.examonlineserv.bean.QuestionInfoExample;
import java.util.List;

import com.volcano.examonlineserv.bean.SubjectInfo;
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

    @Select("SELECT COUNT(id) FROM questioninfo WHERE subjectId = #{subjectId,jdbcType=INTEGER}")
    int countQuestions(@Param("subjectId") Integer subjectId);

    @Select("SELECT * " +
            "FROM questioninfo " +
            "WHERE subjectId = #{subjectId,jdbcType=INTEGER} ORDER BY RAND() LIMIT #{num,jdbcType=INTEGER}")
    List<QuestionInfo> getRandomQuestions(@Param("subjectId") Integer subjectId, @Param("num") Integer num);

    @Select("SELECT * " +
            "FROM questioninfo " +
            "WHERE subjectId = #{subjectId,jdbcType=INTEGER}")
    List<QuestionInfo> getQuestions(Integer subjectId);

    @Select("SELECT * " +
            "FROM questioninfo " +
            "WHERE description LIKE #{s,jdbcType=VARCHAR}")
    List<QuestionInfo> searchQuestion(@Param("s") String s);

    @Select("SELECT * FROM subjectinfo")
    List<SubjectInfo> getSubjects();
}