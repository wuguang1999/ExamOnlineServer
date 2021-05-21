package com.volcano.examonlineserv.controller;

import com.volcano.examonlineserv.bean.Comments;
import com.volcano.examonlineserv.bean.CommentsResponse;
import com.volcano.examonlineserv.bean.QuestionInfo;
import com.volcano.examonlineserv.bean.SubjectInfo;
import com.volcano.examonlineserv.config.Result;
import com.volcano.examonlineserv.config.ResultCode;
import com.volcano.examonlineserv.service.QuestionService;
import com.volcano.examonlineserv.utils.JwtUtil;
import lombok.Data;
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
    @GetMapping("/api/v1/questions/subject")
    public Result getSubjects() {
        List<SubjectInfo> list = questionService.getSubjects();
        return Result.getListResult(list);
    }

    /**
     * 获取试题列表
     * @return
     */
    @GetMapping("/api/v1/questions")
    public Result getQuestions(@RequestParam String subjectName) {
        List<QuestionInfo> list = questionService.getQuestions(subjectName);
        return Result.getListResult(list);
    }

    /**
     * 搜索试题
     * @param content
     * @return
     */
    @GetMapping("/api/v1/questions/search")
    public Result searchQuestion(@RequestParam String content) {
        Result res;
        if(content == null || content.equals("")) {
            res = Result.failure(ResultCode.PARAM_IS_BLANK);
            return res;
        }
        List<QuestionInfo> list = questionService.searchQuestion(content);
        return Result.getListResult(list);
    }

    /**
     * 根据试题id获得评论列表
     * @param id
     * @return
     */
    @GetMapping("/api/v1/questions/comments")
    public Result getQuestionComments(@RequestParam int id) {
        List<CommentsResponse> list = questionService.getQuestionComments(id);
        return Result.getListResult(list);
    }

    /**
     * 随机抽取num道试题组成试卷
     * @param num
     * @return Result<List<QuestionInfo>>
     *
     */
    @GetMapping("/api/v1/questions/random")
    public Result getRandomQuestions(@RequestParam String subjectName , @RequestParam String num) {
        Result res;
        if(null == num || num.equals("")) {
            res = Result.failure(ResultCode.PARAM_IS_INVALID);
            return res;
        }
        List<QuestionInfo> list = questionService.getRandomQuestions(subjectName, num);
        return Result.getListResult(list);
    }

    /**
     * 填写表单上传试题
     * @return
     */
    @PostMapping("/api/v1/questions/edit")
    public Result uploadQuestion(@RequestHeader String authorization, @RequestBody UploadBean uploadBean) {
        Result res;
        if(null == authorization || null == JwtUtil.validateToken(authorization)) {
            res = Result.failure(ResultCode.DATA_IS_WRONG);
            return res;
        }
        QuestionInfo questionInfo = new QuestionInfo();
        questionInfo.setSource(uploadBean.source); questionInfo.setLevel(uploadBean.level);
        questionInfo.setKeywords(uploadBean.keywords); questionInfo.setDescription(uploadBean.description);
        questionInfo.setType(uploadBean.type); questionInfo.setImg(null);
        questionInfo.setOptiona(uploadBean.optiona); questionInfo.setOptionb(uploadBean.optionb);
        questionInfo.setOptionc(uploadBean.optionc); questionInfo.setOptiond(uploadBean.optiond);
        questionInfo.setOptione(uploadBean.optione); questionInfo.setCorrectanswer(uploadBean.correctanswer);
        questionInfo.setAnalysis(uploadBean.analysis);
        res = questionService.uploadQuestion(questionInfo, uploadBean.subjectName);
        return res;
    }

    @GetMapping("/api/v1/questions/commend")
    public Result getCommendQuestions(@RequestParam Integer subjectId ,@RequestParam String keywords) {
        List<QuestionInfo> list = questionService.getCommendQuestions(subjectId, keywords);
        return Result.getListResult(list);
    }

    @PostMapping("/api/v1/questions/uploadcomment")
    public Result uploadQuestionComment(@RequestHeader String authorization, @RequestBody CommentBean commentBean) {
        Result res;
        if(null == authorization || null == JwtUtil.validateToken(authorization)) {
            res = Result.failure(ResultCode.DATA_IS_WRONG);
            return res;
        }
        Comments comments = new Comments();
        comments.setTargetid(commentBean.targetId); comments.setType(commentBean.type);
        comments.setDescription(commentBean.description);
        res = questionService.uploadQuestionComment(JwtUtil.validateToken(authorization), comments);
        return res;
    }

    @Data
    public static class UploadBean {
        String subjectName;
        String source;
        String level;
        String keywords;
        String description;
        String type;
        String img;
        String optiona;
        String optionb;
        String optionc;
        String optiond;
        String optione;
        String correctanswer;
        String analysis;
    }

    @Data
    public static class CommentBean {
        Integer targetId;
        String type;
        String description;
        String img;
    }
}
