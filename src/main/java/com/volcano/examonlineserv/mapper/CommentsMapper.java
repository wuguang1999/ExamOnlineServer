package com.volcano.examonlineserv.mapper;

import com.volcano.examonlineserv.bean.Comments;
import com.volcano.examonlineserv.bean.CommentsExample;
import java.util.List;

import com.volcano.examonlineserv.bean.CommentsResponse;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface CommentsMapper {
    long countByExample(CommentsExample example);

    int deleteByExample(CommentsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Comments record);

    int insertSelective(Comments record);

    List<Comments> selectByExampleWithBLOBs(CommentsExample example);

    List<Comments> selectByExample(CommentsExample example);

    Comments selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Comments record, @Param("example") CommentsExample example);

    int updateByExampleWithBLOBs(@Param("record") Comments record, @Param("example") CommentsExample example);

    int updateByExample(@Param("record") Comments record, @Param("example") CommentsExample example);

    int updateByPrimaryKeySelective(Comments record);

    int updateByPrimaryKeyWithBLOBs(Comments record);

    int updateByPrimaryKey(Comments record);

    @Select("SELECT comments.id,userName,description,img,avatar,zan,comments.createAt " +
            "FROM userinfo, comments " +
            "WHERE type = #{type,jdbcType=VARCHAR} AND userinfo.id = comments.userId AND comments.targetId = #{id,jdbcType=INTEGER}")
    List<CommentsResponse> getArticleComments(@Param("type") String type, @Param("id") Integer id);

    @Select("SELECT comments.id,userName,description,img,avatar,zan,comments.createAt " +
            "FROM userinfo, comments " +
            "WHERE type = #{type,jdbcType=VARCHAR} AND userinfo.id = comments.userId AND comments.targetId = #{id,jdbcType=INTEGER}")
    List<CommentsResponse> getQuestionComments(@Param("type") String type, @Param("id") Integer id);
}