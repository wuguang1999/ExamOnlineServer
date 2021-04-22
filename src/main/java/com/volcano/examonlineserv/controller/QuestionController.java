package com.volcano.examonlineserv.controller;

import com.volcano.examonlineserv.bean.CommentsResponse;
import com.volcano.examonlineserv.bean.QuestionInfo;
import com.volcano.examonlineserv.bean.SubjectInfo;
import com.volcano.examonlineserv.config.Result;
import com.volcano.examonlineserv.config.ResultCode;
import com.volcano.examonlineserv.service.QuestionService;
import com.volcano.examonlineserv.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    /**
     * 获取学科列表
     * @return
     */
    @GetMapping("/api/v1/subjects")
    public Result getSubjects() {
        Result res;
        List<SubjectInfo> list = questionService.getSubjects();
        if(list == null || list.isEmpty()) {
            res = Result.failure(ResultCode.SYSTEM_INNER_ERROR);
        }else {
            res = Result.success(list);
        }
        return res;
    }

    /**
     * 获取试题列表
     * @return
     */
    @GetMapping("/api/v1/questions")
    public Result getQuestions(@RequestParam Integer subjectId) {
        Result res;
        List<QuestionInfo> list = questionService.getQuestions(subjectId);
        if(list == null || list.isEmpty()) {
            res = Result.failure(ResultCode.RESULE_DATA_NONE);
        }else {
            res = Result.success(list);
        }
        return res;
    }

    /**
     * 搜索试题
     * @param content
     * @return
     */
    @GetMapping("/api/v1/question")
    public Result searchQuestion(@RequestParam String content) {
        Result res;
        if(content == null || content.equals("")) {
            res = Result.failure(ResultCode.PARAM_IS_BLANK);
            return res;
        }
        List<QuestionInfo> list = questionService.searchQuestion(content);
        if(list == null || list.isEmpty()) {
            res = Result.failure(ResultCode.SYSTEM_INNER_ERROR);
        }else {
            res = Result.success(list);
        }
        return res;
    }

    /**
     * 根据试题id获得评论列表
     * @param id
     * @return
     */
    @GetMapping("/api/v1/question/comment")
    public Result getQuestionComments(@RequestParam int id) {
        Result res;
        List<CommentsResponse> list = questionService.getQuestionComments(id);
        if(list == null || list.isEmpty()) {
            res = Result.failure(ResultCode.SYSTEM_INNER_ERROR);
        }else {
            res = Result.success(list);
        }
        return res;
    }

    /**
     * 搜索框热词
     * @return
     */
    @GetMapping("/api/v1/question/hotkey")
    public Result getQuestionHotKey() {
        Result result = new Result();
        return result;
    }


    /**
     * 随机抽取num道试题组成试卷
     * @param num
     * @return Result<List<QuestionInfo>>
     *
     */
    @GetMapping("/api/v1/question/random")
    public Result getRandomQuestions(@RequestParam Integer subjectId , @RequestParam String num) {
        Result res;
        if(null == num || num.equals("")) {
            res = Result.failure(ResultCode.PARAM_IS_INVALID);
            return res;
        }
        List<QuestionInfo> list = questionService.getRandomQuestions(subjectId, num);
        if(list == null || list.isEmpty()) {
            res = Result.failure(ResultCode.DATA_IS_WRONG);
        }else {
            res = Result.success(list);
        }
        return res;
    }

    /**
     * 填写表单上传试题
     * @return
     */
    @PostMapping("/api/v1/question/edit")
    public Result uploadQuestion(@RequestHeader String authorization, @RequestBody QuestionInfo questionInfo) {
        Result res;
        if(null == authorization || null == JwtUtil.validateToken(authorization)) {
            res = Result.failure(ResultCode.DATA_IS_WRONG);
            return res;
        }
        res = questionService.uploadQuestion(questionInfo);
        return res;
    }


}
