package com.volcano.examonlineserv.controller;

import com.volcano.examonlineserv.bean.ArticleInfo;
import com.volcano.examonlineserv.bean.CommentsResponse;
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
    @GetMapping("/api/v1/articles")
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

    /**
     * 获取论坛热榜文章列表
     * @return
     */
    @GetMapping("api/v1/articles/hot")
    public Result getHotArticles() {
        Result res = new Result();
        return res;
    }

    /**
     * 根据文章id获取评论列表
     * @param id
     * @return
     */
    @GetMapping("api/v1/article")
    public Result getArticleComments(@RequestParam int id) {
        Result res;
        List<CommentsResponse> list = articleService.getArticleComments(id);
        if(list == null || list.isEmpty()) {
            res = Result.failure(ResultCode.SYSTEM_INNER_ERROR);
        }else {
            res = Result.success(list);
        }
        return res;
    }

    /**
     * 发布文章
     * @param authorization
     * @param articleTmp
     * @return
     */
    @PostMapping("api/v1/article/edit")
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

    /**
     * 搜索框热词
     * @return
     */
    @GetMapping("api/v1/article/hotkey")
    public Result getArticleHotKey() {
        return new Result();
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
