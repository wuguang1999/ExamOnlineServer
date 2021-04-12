package com.volcano.examonlineserv.service;

import com.volcano.examonlineserv.bean.QuestionInfo;
import com.volcano.examonlineserv.bean.QuestionInfoExample;
import com.volcano.examonlineserv.mapper.QuestionInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired(required = false)
    QuestionInfoMapper questionInfoMapper;

    public List<QuestionInfo> getRandomQuestions(String num) {
        int count = questionInfoMapper.countQuestions();
        List<QuestionInfo> records;
        if(Integer.parseInt(num) > count) {
            return null;
        }
        records = questionInfoMapper.getRandomQuestions(Integer.parseInt(num));
        return records;
    }
}
