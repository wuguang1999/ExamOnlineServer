package com.volcano.examonlineserv.mapper;

import com.volcano.examonlineserv.bean.ArticleComments;
import com.volcano.examonlineserv.bean.ArticleCommentsExample;
import java.util.List;

import com.volcano.examonlineserv.bean.CommentsResponse;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ArticleCommentsMapper {
    long countByExample(ArticleCommentsExample example);

    int deleteByExample(ArticleCommentsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ArticleComments record);

    int insertSelective(ArticleComments record);

    List<ArticleComments> selectByExample(ArticleCommentsExample example);

    ArticleComments selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ArticleComments record, @Param("example") ArticleCommentsExample example);

    int updateByExample(@Param("record") ArticleComments record, @Param("example") ArticleCommentsExample example);

    int updateByPrimaryKeySelective(ArticleComments record);

    int updateByPrimaryKey(ArticleComments record);

    @Select("SELECT userName,description,img,avatar,articlecomments.createAt " +
            "FROM userinfo,articlecomments " +
            "WHERE userId = userinfo.id and articlecomments.articleId = #{id,jdbcType=INTEGER}")
    List<CommentsResponse> getArticleComments(int id);
}