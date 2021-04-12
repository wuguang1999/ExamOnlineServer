package com.volcano.examonlineserv.service;

import com.volcano.examonlineserv.bean.ArticleInfo;
import com.volcano.examonlineserv.bean.Userinfo;
import com.volcano.examonlineserv.config.Result;
import com.volcano.examonlineserv.config.ResultCode;
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


    public List<ArticleInfo> getArticles() {
        return articleInfoMapper.getArticles();
    }

    public Result uploadArticle(String userphone, String title, String desc, String img) {
        ArticleInfo articleInfo = new ArticleInfo();
        articleInfo.setUserphone(userphone);
        articleInfo.setDescription(desc);
        articleInfo.setTitle(title);
        if (null != img && !img.equals("")) {
            articleInfo.setImg(img);
        }
        Date time = new Date(new java.util.Date().getTime());
        articleInfo.setCreateat(time);
        articleInfo.setUsername(userinfoMapper.selectUserInfoByPhone(userphone).getUsername());
        if(articleInfoMapper.insert(articleInfo) > 0) {
            return Result.success();
        }else {
            return Result.failure(ResultCode.SYSTEM_INNER_ERROR);
        }
    }
}
