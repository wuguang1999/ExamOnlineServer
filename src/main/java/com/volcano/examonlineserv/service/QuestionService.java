package com.volcano.examonlineserv.service;

import com.volcano.examonlineserv.bean.CommentsResponse;
import com.volcano.examonlineserv.bean.QuestionInfo;
import com.volcano.examonlineserv.bean.QuestionInfoExample;
import com.volcano.examonlineserv.bean.SubjectInfo;
import com.volcano.examonlineserv.config.Result;
import com.volcano.examonlineserv.config.ResultCode;
import com.volcano.examonlineserv.mapper.CommentsMapper;
import com.volcano.examonlineserv.mapper.QuestionInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired(required = false)
    QuestionInfoMapper questionInfoMapper;

    @Autowired(required = false)
    CommentsMapper questionCommentsMapper;

    public List<SubjectInfo> getSubjects() {
        return questionInfoMapper.getSubjects();
    }

    public List<QuestionInfo> getQuestions(Integer subjectId) {
        List<QuestionInfo> records = questionInfoMapper.getQuestions(subjectId);
        return records;
    }

    public List<QuestionInfo> searchQuestion(String content) {
        List<QuestionInfo> records = questionInfoMapper.searchQuestion("%" + content + "%");
        return records;
    }

    public List<CommentsResponse> getQuestionComments(int id) {
        List<CommentsResponse> comments = questionCommentsMapper.getQuestionComments("试题",id);
        return comments;
    }

    public List<QuestionInfo> getRandomQuestions(Integer subjectId, String num) {
        int count = questionInfoMapper.countQuestions(subjectId);
        List<QuestionInfo> records;
        if(Integer.parseInt(num) > count) {
            return null;
        }
        records = questionInfoMapper.getRandomQuestions(subjectId, Integer.parseInt(num));
        return records;
    }

    public Result uploadQuestion(QuestionInfo questionInfo) {
        if(questionInfoMapper.insert(questionInfo) > 0) {
            return Result.success();
        }else {
            return Result.failure(ResultCode.SYSTEM_INNER_ERROR);
        }
    }

    public List<QuestionInfo> getCommendQuestions(Integer subjectId, String keywords) {
        return questionInfoMapper.getCommendQuestions(subjectId, "%" + keywords + "%");
    }
}
