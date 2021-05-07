package com.volcano.examonlineserv.controller;

import com.volcano.examonlineserv.bean.ArticleInfo;
import com.volcano.examonlineserv.bean.RankingResponse;
import com.volcano.examonlineserv.bean.Userinfo;
import com.volcano.examonlineserv.config.Result;
import com.volcano.examonlineserv.config.ResultCode;
import com.volcano.examonlineserv.service.UserService;
import com.volcano.examonlineserv.utils.JwtUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 根据id获取用户信息
     * @param param
     * @return
     */
    @GetMapping("/api/v1/userinfo/id")
    public Result getUserinfoById(@RequestParam Integer param) {
        Result res;
        if(null == param) {
            res = Result.failure(ResultCode.PARAM_IS_INVALID);
            return res;
        }
        Userinfo userinfo = userService.getUserInfoById(param);
        if(userinfo == null) {
            res = Result.failure(ResultCode.USER_NOT_EXIST);
        }else {
            res = Result.success(userinfo);
        }
        return res;
    }

    /**
     * 修改用户信息 账号、昵称、头像
     * @param token
     * @param userTmp
     * @return
     */
    @PostMapping("/api/v1/userinfo/edit")
    public Result editUserInfo(@RequestHeader("authorization") String token ,@RequestBody UserTmp userTmp) {
        Userinfo userinfo = new Userinfo();
        userinfo.setId(JwtUtil.validateToken(token));
        userinfo.setPhone(userTmp.getPhone());
        userinfo.setUsername(userTmp.getUsername());
        userinfo.setAvatar(userTmp.getAvatar());
        Result res = new Result();
        res.setResultCode(userService.editUserInfo(userinfo));
        return res;
    }

    /**
     * 修改用户密码
     * @param token
     * @param userPwd
     * @return
     */
    @PostMapping("/api/v1/userinfo/pwd")
    public Result editUserPwd(@RequestHeader("authorization") String token ,@RequestBody UserPwd userPwd) {
        Userinfo userinfo = new Userinfo();
        userinfo.setId(JwtUtil.validateToken(token));
        Result res = new Result();
        res.setResultCode(userService.editUserPwd(userinfo, userPwd.oldPwd, userPwd.newPwd));
        return res;
    }

    /**
     * 注册
     * @param userTmp
     * @return
     */
    @PostMapping("/api/v1/userinfo/register")
    public Result userRegister(@RequestBody UserTmp userTmp) {
        Userinfo userinfo = new Userinfo();
        userinfo.setPhone(userTmp.phone);
        userinfo.setUsername(userTmp.username);
        userinfo.setPwd(userTmp.pwd);
        userinfo.setAvatar(userTmp.avatar);
        Result res = new Result();
        if(userService.userRegister(userinfo)) {
            res.setResultCode(ResultCode.SUCCESS);
        }else {
            res.setResultCode(ResultCode.USER_HAS_EXISTED);
        }
        return res;
    }

    /**
     * 登录
     * @param userTmp
     * @return
     */
    @PostMapping("/api/v1/userinfo/login")
    public Result userLogin(@RequestBody UserTmp userTmp) {
        Result res = new Result();
        if(null == userTmp || null == userTmp.phone) {
            res.setResultCode(ResultCode.PARAM_IS_BLANK);
            return res;
        }
        Integer[] code = userService.userLogin(userTmp.phone, userTmp.pwd);
        if(code[0] == 0){
            res.setResultCode(ResultCode.SYSTEM_INNER_ERROR);
        }else if(code[0] == 2){
            res.setResultCode(ResultCode.USER_LOGIN_ERROR);
        }else {
            res.setResultCode(ResultCode.SUCCESS);
            String token = JwtUtil.generateToken(code[1]);
            HashMap<String, String> map = new HashMap<>();
            map.put("token", token);
            map.put("id", String.valueOf(code[1]));
            res = Result.success(map);
        }
        return res;
    }

    /**
     * 我的文章发布历史
     * @param authorization
     * @return
     */
    @GetMapping("/api/v1/userinfo/articles")
    public Result getMyArticles(@RequestHeader String authorization) {
        Result res;
        Integer id = JwtUtil.validateToken(authorization);
        if(id == null) {
            res = Result.failure(ResultCode.PARAM_IS_INVALID);
            return res;
        }
        List<ArticleInfo> list = userService.getMyArticles(id);
        if(list == null || list.isEmpty()) {
            res = Result.failure(ResultCode.SYSTEM_INNER_ERROR);
        }else {
            res = Result.success(list);
        }
        return res;
    }

    @GetMapping("/api/v1/userinfo/ranking")
    public Result getRankings() {
        Result res;
        List<RankingResponse> list = userService.getRankings();
        if(list == null || list.isEmpty()) {
            res = Result.failure(ResultCode.SYSTEM_INNER_ERROR);
        }else {
            res = Result.success(list);
        }
        return res;
    }

    @Data
    public static class UserTmp {
        private String phone;
        private String username;
        private String pwd;
        private byte[] avatar;
    }

    @Data
    public static class UserPwd {
        private String oldPwd;
        private String newPwd;
    }
}
