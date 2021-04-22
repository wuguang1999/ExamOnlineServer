package com.volcano.examonlineserv.service;

import com.volcano.examonlineserv.bean.*;
import com.volcano.examonlineserv.config.Result;
import com.volcano.examonlineserv.config.ResultCode;
import com.volcano.examonlineserv.mapper.ArticleCommentsMapper;
import com.volcano.examonlineserv.mapper.ArticleInfoMapper;
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
    ArticleCommentsMapper articleCommentsMapper;


    public List<ArticleInfo> getArticles() {
        return articleInfoMapper.getArticles();
    }

    public Result uploadArticle(String userPhone, ArticleInfo articleInfo) {
        Date time = new Date(new java.util.Date().getTime());
        articleInfo.setCreateat(time);
        UserinfoExample example = new UserinfoExample();
        example.createCriteria().andPhoneEqualTo(userPhone);
        List<Userinfo> userInfos = userinfoMapper.selectByExample(example);
        articleInfo.setUserid(userInfos.get(0).getId());
        articleInfo.setUsername(userInfos.get(0).getUsername());
        if(articleInfoMapper.insert(articleInfo) > 0) {
            return Result.success();
        }else {
            return Result.failure(ResultCode.SYSTEM_INNER_ERROR);
        }
    }

    public List<CommentsResponse> getArticleComments(int id) {
        return articleCommentsMapper.getArticleComments(id);
    }

    public List<ArticleInfo> getHotArticles() {
        return articleInfoMapper.getHotArticles();
    }
}
