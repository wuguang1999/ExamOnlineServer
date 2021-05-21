package com.volcano.examonlineserv.mapper;

import com.volcano.examonlineserv.bean.QuestionInfo;
import com.volcano.examonlineserv.bean.QuestionInfoExample;
import java.util.List;

import com.volcano.examonlineserv.bean.SubjectInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface QuestionInfoMapper {
    long countByExample(QuestionInfoExample example);

    int deleteByExample(QuestionInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(QuestionInfo record);

    int insertSelective(QuestionInfo record);

    List<QuestionInfo> selectByExampleWithBLOBs(QuestionInfoExample example);

    List<QuestionInfo> selectByExample(QuestionInfoExample example);

    QuestionInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") QuestionInfo record, @Param("example") QuestionInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") QuestionInfo record, @Param("example") QuestionInfoExample example);

    int updateByExample(@Param("record") QuestionInfo record, @Param("example") QuestionInfoExample example);

    int updateByPrimaryKeySelective(QuestionInfo record);

    int updateByPrimaryKeyWithBLOBs(QuestionInfo record);

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
    List<QuestionInfo> getQuestions(@Param("subjectId") Integer subjectId);

    @Select("SELECT * " +
            "FROM questioninfo " +
            "WHERE description LIKE #{s,jdbcType=VARCHAR}")
    List<QuestionInfo> searchQuestion(@Param("s") String s);

    @Select("SELECT * FROM subjectinfo")
    List<SubjectInfo> getSubjects();

    @Select("SELECT * FROM questioninfo WHERE subjectId = #{subjectId,jdbcType=INTEGER} AND description LIKE #{keywords,jdbcType=VARCHAR}")
    List<QuestionInfo> getCommendQuestions(@Param("subjectId") Integer subjectId, @Param("keywords") String keywords);

    @Select("SELECT id FROM subjectinfo WHERE subjectName = #{subjectName,jdbcType=VARCHAR}")
    Integer getSubjectId(@Param("subjectName") String name);

    @Update("UPDATE questioninfo " +
            "SET commentNums = commentNums + 1 " +
            "WHERE id = #{id,jdbcType=INTEGER}")
    void increaseCommentNums(@Param("id") Integer id);
}