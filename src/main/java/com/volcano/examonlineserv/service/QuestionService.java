package com.volcano.examonlineserv.service;

import com.volcano.examonlineserv.bean.Comments;
import com.volcano.examonlineserv.bean.CommentsResponse;
import com.volcano.examonlineserv.bean.QuestionInfo;
import com.volcano.examonlineserv.bean.SubjectInfo;
import com.volcano.examonlineserv.config.Result;
import com.volcano.examonlineserv.config.ResultCode;
import com.volcano.examonlineserv.mapper.CommentsMapper;
import com.volcano.examonlineserv.mapper.QuestionInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
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

    public List<QuestionInfo> getQuestions(String subjectName) {
        List<QuestionInfo> records = questionInfoMapper.getQuestions(questionInfoMapper.getSubjectId(subjectName));
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

    public List<QuestionInfo> getRandomQuestions(String subjectName, String num) {
        int count = questionInfoMapper.countQuestions(questionInfoMapper.getSubjectId(subjectName));
        List<QuestionInfo> records;
        if(Integer.parseInt(num) > count) {
            return null;
        }
        records = questionInfoMapper.getRandomQuestions(questionInfoMapper.getSubjectId(subjectName), Integer.parseInt(num));
        return records;
    }

    public Result uploadQuestion(QuestionInfo questionInfo, String subjectName) {
        Integer subjectId = questionInfoMapper.getSubjectId(subjectName);
        questionInfo.setSubjectid(subjectId);
        if(questionInfoMapper.insert(questionInfo) > 0) {
            return Result.success();
        }else {
            return Result.failure(ResultCode.SYSTEM_INNER_ERROR);
        }
    }

    public List<QuestionInfo> getCommendQuestions(Integer subjectId, String keywords) {
        return questionInfoMapper.getCommendQuestions(subjectId, "%" + keywords + "%");
    }

    public Result uploadQuestionComment(Integer userId, Comments comments) {
        Date time = new Date(new java.util.Date().getTime());
        comments.setCreateat(time);
        comments.setUserid(userId);
        if(questionCommentsMapper.insert(comments) > 0) {
            questionInfoMapper.increaseCommentNums(comments.getTargetid());
            return Result.success();
        }else {
            return Result.failure(ResultCode.SYSTEM_INNER_ERROR);
        }
    }
}
