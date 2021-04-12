package com.volcano.examonlineserv.controller;

import com.volcano.examonlineserv.bean.QuestionInfo;
import com.volcano.examonlineserv.config.Result;
import com.volcano.examonlineserv.config.ResultCode;
import com.volcano.examonlineserv.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    /**
     * 随机抽取num道试题组成试卷
     * @param num
     * @return Result<List<QuestionInfo>>
     *
     */
    @GetMapping("/questions/random")
    public Result getRandomQuestions(@RequestParam String num) {
        Result res;
        if(null == num || num.equals("")) {
            res = Result.failure(ResultCode.PARAM_IS_INVALID);
            return res;
        }
        List<QuestionInfo> list = questionService.getRandomQuestions(num);
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
    @PostMapping("/questions")
    public Result uploadQuestion() {
        Result res = Result.failure(ResultCode.RESULE_DATA_NONE);
        return res;
    }
}
