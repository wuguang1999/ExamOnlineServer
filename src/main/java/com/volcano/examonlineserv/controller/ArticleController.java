package com.volcano.examonlineserv.controller;

import com.volcano.examonlineserv.bean.ArticleInfo;
import com.volcano.examonlineserv.config.Result;
import com.volcano.examonlineserv.config.ResultCode;
import com.volcano.examonlineserv.service.ArticleService;
import com.volcano.examonlineserv.utils.JwtUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 获取论坛文章列表
     * @return
     */
    @GetMapping("/articles")
    public Result getArticles() {
        Result res;
        List<ArticleInfo> list = articleService.getArticles();
        if(list == null || list.isEmpty()) {
            res = Result.failure(ResultCode.RESULE_DATA_NONE);
        }else {
            res = Result.success(list);
        }
        return res;
    }

    @PostMapping("/articles/edit")
    public Result uploadArticle(@RequestHeader String authorization, @RequestBody ArticleTmp articleTmp) {
        Result res;
        if(null == authorization || null == JwtUtil.validateToken(authorization)) {
            res = Result.failure(ResultCode.DATA_IS_WRONG);
            return res;
        }
        String userphone = JwtUtil.validateToken(authorization);
        String title = articleTmp.getTitle();
        String desc = articleTmp.getDesc();
        String img = articleTmp.getImg();
        res = articleService.uploadArticle(userphone, title, desc, img);
        return res;
    }

    @Data
    public static class ArticleTmp{

        private String title;

        private String description;

        private String img;

        public String getTitle() {
            return title;
        }

        public String getDesc() {
            return description;
        }

        public String getImg() {
            return img;
        }
    }


}
