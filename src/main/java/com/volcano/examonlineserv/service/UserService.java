package com.volcano.examonlineserv.service;

import com.volcano.examonlineserv.bean.Userinfo;
import com.volcano.examonlineserv.config.ResultCode;
import com.volcano.examonlineserv.mapper.UserinfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Service
public class UserService {

    @Autowired(required = false)
    private UserinfoMapper userinfoMapper;

    public Userinfo getUserInfo(String phone) {
        return userinfoMapper.selectUserInfoByPhone(phone);
    }

    @Transactional
    public boolean userRegister(String phone, String pwd, String username, String avatar) {
        Userinfo user = userinfoMapper.selectUserInfoByPhone(phone);
        if(null != user) {
            return false;
        }
        user = new Userinfo();
        user.setPhone(phone);
        user.setPwd(pwd);
        user.setUsername(username);
        user.setAvatar(avatar);
        Date time = new Date(new java.util.Date().getTime());
        user.setCreateat(time);
        return userinfoMapper.insert(user) > 0;
    }

    @Transactional
    public ResultCode userLogin(String phone, String pwd) {
        Userinfo userinfo = userinfoMapper.selectUserInfoByPhone(phone);
        if(null == userinfo) {
            return ResultCode.USER_NOT_EXIST;
        }
        if(userinfo.getPwd().equals(pwd)) {
            return ResultCode.SUCCESS;
        }else {
            return ResultCode.USER_LOGIN_ERROR;
        }
    }
}
