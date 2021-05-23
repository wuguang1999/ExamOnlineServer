package com.volcano.examonlineserv.service;

import com.volcano.examonlineserv.bean.*;
import com.volcano.examonlineserv.config.Result;
import com.volcano.examonlineserv.config.ResultCode;
import com.volcano.examonlineserv.mapper.ArticleInfoMapper;
import com.volcano.examonlineserv.mapper.CommentsMapper;
import com.volcano.examonlineserv.mapper.UserinfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class ArticleService {

    @Autowired(required = false)
    ArticleInfoMapper articleInfoMapper;

    @Autowired(required = false)
    UserinfoMapper userinfoMapper;

    @Autowired(required = false)
    CommentsMapper articleCommentsMapper;


    public List<ArticleInfo> getArticles() {
        return articleInfoMapper.getArticles();
    }

    public List<ArticleInfo> getHotArticles() {
        return articleInfoMapper.getHotArticles();
    }

    public List<CommentsResponse> getArticleComments(int id) {
        return articleCommentsMapper.getArticleComments("文章",id);
    }

    public Result uploadArticle(Integer userId, ArticleInfo articleInfo) {
        Date time = new Date(new java.util.Date().getTime());
        articleInfo.setCreateat(time);
        Userinfo userinfo = userinfoMapper.selectByPrimaryKey(userId);
        articleInfo.setUserid(userId);
        articleInfo.setUsername(userinfo.getUsername());
        if(articleInfoMapper.insertSelective(articleInfo) > 0) {
            return Result.success();
        }else {
            return Result.failure(ResultCode.SYSTEM_INNER_ERROR);
        }
    }

    public List<ArticleInfo> searchArticle(String content) {
        return articleInfoMapper.searchArticles("%" + content + "%");
    }

    public Result uploadArticleComment(Integer userId, Comments comments) {
        Date time = new Date(new java.util.Date().getTime());
        comments.setCreateat(time);
        comments.setUserid(userId);
        if(articleCommentsMapper.insertSelective(comments) > 0) {
            articleInfoMapper.increaseCommentNums(comments.getTargetid());
            return Result.success();
        }else {
            return Result.failure(ResultCode.SYSTEM_INNER_ERROR);
        }
    }

    public Result increaseArticleZan(Integer articleId) {
        ArticleInfo articleInfo = articleInfoMapper.selectByPrimaryKey(articleId);
        articleInfo.setZannums(articleInfo.getZannums() + 1);
        if(articleInfoMapper.updateByPrimaryKeySelective(articleInfo) > 0) {
            return Result.success();
        }else {
            return Result.failure(ResultCode.SYSTEM_INNER_ERROR);
        }
    }

    public Result decreaseArticleZan(Integer articleId) {
        ArticleInfo articleInfo = articleInfoMapper.selectByPrimaryKey(articleId);
        articleInfo.setZannums(articleInfo.getZannums() - 1);
        if(articleInfoMapper.updateByPrimaryKeySelective(articleInfo) > 0) {
            return Result.success();
        }else {
            return Result.failure(ResultCode.SYSTEM_INNER_ERROR);
        }
    }
}
