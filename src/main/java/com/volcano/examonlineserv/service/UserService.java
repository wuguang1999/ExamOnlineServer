package com.volcano.examonlineserv.service;

import com.volcano.examonlineserv.bean.*;
import com.volcano.examonlineserv.config.Result;
import com.volcano.examonlineserv.config.ResultCode;
import com.volcano.examonlineserv.mapper.ArticleInfoMapper;
import com.volcano.examonlineserv.mapper.UserinfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired(required = false)
    private UserinfoMapper userinfoMapper;

    @Autowired(required = false)
    private ArticleInfoMapper articleInfoMapper;

    public Userinfo getUserInfoById(Integer id) {
        Userinfo userinfo = userinfoMapper.selectByPrimaryKey(id);
        return userinfo;
    }


    public ResultCode editUserInfo(Userinfo userinfo) {
        String phone = userinfo.getPhone();
        if(phone != null || phone != "") {
            if(userinfoMapper.selectByPhone(phone) != null) {
                return ResultCode.USER_HAS_EXISTED;
            }
        }
        if(userinfoMapper.updateByPrimaryKeySelective(userinfo) == 1) {
            return ResultCode.SUCCESS;
        }else {
            return ResultCode.SYSTEM_INNER_ERROR;
        }
    }

    public ResultCode editUserPwd(Userinfo userinfo, String oldPwd, String newPwd) {
        Userinfo user = userinfoMapper.selectByPrimaryKey(userinfo.getId());
        if(user.getPwd().equals(oldPwd)) {
            user.setPwd(newPwd);
            if(userinfoMapper.updateByPrimaryKeySelective(user) == 1) {
                return ResultCode.SUCCESS;
            }else {
                return ResultCode.SYSTEM_INNER_ERROR;
            }
        }
        return ResultCode.USER_LOGIN_ERROR;
    }

    @Transactional
    public boolean userRegister(Userinfo u) {
        UserinfoExample example = new UserinfoExample();
        example.createCriteria().andPhoneEqualTo(u.getPhone());
        List<Userinfo> userinfo = userinfoMapper.selectByExample(example);
        Userinfo user = (userinfo == null || userinfo.isEmpty()) ? null : userinfo.get(0);
        if(null != user) {
            return false;
        }
        user = new Userinfo();
        user.setPhone(u.getPhone());
        user.setPwd(u.getPwd());
        user.setUsername(u.getUsername());
        user.setAvatar(u.getAvatar());
        Date time = new Date(new java.util.Date().getTime());
        user.setCreateat(time);
        return userinfoMapper.insert(user) > 0;
    }

    @Transactional
    public Integer[] userLogin(String phone, String pwd) {
        UserinfoExample example = new UserinfoExample();
        example.createCriteria().andPhoneEqualTo(phone);
        List<Userinfo> userinfos = userinfoMapper.selectByExample(example);
        Userinfo userinfo = (userinfos == null || userinfos.isEmpty()) ? null : userinfos.get(0);
        if(null == userinfo) {
            return new Integer[]{0,0};
        }
        if(userinfo.getPwd().equals(pwd)) {
            return new Integer[]{1,userinfo.getId()};
        }else {
            return new Integer[]{2,0};
        }
    }

    public List<ArticleInfo> getMyArticles(Integer id) {
        ArticleInfoExample example = new ArticleInfoExample();
        example.createCriteria().andUseridEqualTo(id);
        return articleInfoMapper.selectByExample(example);
    }

    public List<RankingResponse> getRankings() {
        return userinfoMapper.getRankings();
    }

    public Result uploadAvatar(Integer userId, String path) {
        Userinfo userinfo = userinfoMapper.selectByPrimaryKey(userId);
        userinfo.setAvatar(path);
        if(userinfoMapper.updateByPrimaryKeySelective(userinfo) > 0) {
            return Result.success();
        }else {
            return Result.failure(ResultCode.SYSTEM_INNER_ERROR);
        }
    }
}
