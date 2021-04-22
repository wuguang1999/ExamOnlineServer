package com.volcano.examonlineserv.service;

import com.volcano.examonlineserv.mapper.HotKeyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    @Autowired(required = false)
    HotKeyMapper mapper;
}
