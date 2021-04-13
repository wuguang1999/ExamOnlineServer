package com.volcano.examonlineserv.mapper;

import com.volcano.examonlineserv.bean.CommentsResponse;
import com.volcano.examonlineserv.bean.QuestionComments;
import com.volcano.examonlineserv.bean.QuestionCommentsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface QuestionCommentsMapper {
    long countByExample(QuestionCommentsExample example);

    int deleteByExample(QuestionCommentsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(QuestionComments record);

    int insertSelective(QuestionComments record);

    List<QuestionComments> selectByExample(QuestionCommentsExample example);

    QuestionComments selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") QuestionComments record, @Param("example") QuestionCommentsExample example);

    int updateByExample(@Param("record") QuestionComments record, @Param("example") QuestionCommentsExample example);

    int updateByPrimaryKeySelective(QuestionComments record);

    int updateByPrimaryKey(QuestionComments record);

    @Select("SELECT userName,description,img,avatar,questioncomments.createAt " +
            "FROM userinfo,questioncomments " +
            "WHERE userId = userinfo.id and questioncomments.questionId = #{id,jdbcType=INTEGER}")
    List<CommentsResponse> getQuestionComments(@Param("id") int id);
}