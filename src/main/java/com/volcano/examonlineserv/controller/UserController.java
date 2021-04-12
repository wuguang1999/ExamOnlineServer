package com.volcano.examonlineserv.controller;

import com.volcano.examonlineserv.bean.Userinfo;
import com.volcano.examonlineserv.config.Result;
import com.volcano.examonlineserv.config.ResultCode;
import com.volcano.examonlineserv.service.UserService;
import com.volcano.examonlineserv.utils.JwtUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userinfo/{phone}")
    public Result getUserinfo(@PathVariable String phone) {
        Result res;
        if(null == phone || phone.equals("")) {
            res = Result.failure(ResultCode.PARAM_IS_INVALID);
            return res;
        }
        Userinfo userinfo = userService.getUserInfo(phone);
        if(userinfo == null) {
            res = Result.failure(ResultCode.USER_NOT_EXIST);
        }else {
            res = Result.success(userinfo);
        }
        return res;
    }

    @PostMapping("/userinfo/register")
    public Result userRegister(@RequestBody UserTmp userTmp) {
        String phone = userTmp.getPhone();
        String pwd = userTmp.getPwd();
        String username = userTmp.getUsername();
        String avatar = userTmp.getAvatar();
        Result res = new Result();
        //本判断在客户端做，此处可以删去
        if(null == phone || null == pwd) {
            res.setResultCode(ResultCode.PARAM_IS_BLANK);
            return res;
        }
        if(userService.userRegister(phone, pwd, username, avatar)) {
            res.setResultCode(ResultCode.SUCCESS);
        }else {
            res.setResultCode(ResultCode.USER_HAS_EXISTED);
        }
        return res;
    }

    @PostMapping("/userinfo/login")
    public Result userLogin(@RequestBody UserTmp userTmp) {
        Result res = new Result();
        if(null == userTmp || null == userTmp.getPhone()) {
            res.setResultCode(ResultCode.PARAM_IS_BLANK);
            return res;
        }
        ResultCode code = userService.userLogin(userTmp.getPhone(), userTmp.getPwd());
        res.setResultCode(code);
        if(code == ResultCode.SUCCESS) {
            // 生成token
            String token = JwtUtil.generateToken(userTmp.getPhone());
            HashMap<String, String> map = new HashMap<>();
            map.put("token", token);
            res = Result.success(map);
        }
        return res;
    }

    @Data
    public static class UserTmp {
        private String phone;
        private String username;
        private String pwd;
        private String avatar;

        public String getPhone() {
            return phone;
        }

        public String getUsername() {
            return username;
        }

        public String getPwd() {
            return pwd;
        }

        public String getAvatar() {
            return avatar;
        }
    }
}
