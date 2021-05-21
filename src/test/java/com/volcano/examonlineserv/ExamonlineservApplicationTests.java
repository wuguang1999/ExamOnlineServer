package com.volcano.examonlineserv;

import com.alibaba.fastjson.JSONArray;
import com.volcano.examonlineserv.bean.Userinfo;
import com.volcano.examonlineserv.mapper.UserinfoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class ExamonlineservApplicationTests {

    @Autowired(required = false)
    UserinfoMapper userinfoMapper;

    @Test
    void contextLoads() {
    }


    @Test
    public void insertUser() throws IOException {
        Userinfo userinfo = new Userinfo();
        FileInputStream fis = new FileInputStream("d://group.png");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BufferedImage read = ImageIO.read(fis);
        ImageIO.write(read, "png", baos);
        byte[] bytes = baos.toByteArray();
        userinfo.setUsername("test");
        userinfo.setPwd("123");
        userinfo.setPhone("000000");
        userinfo.setCreateat(new Date());
        userinfo.setAvatar(bytes);
        userinfoMapper.insert(userinfo);
        baos.close();
        fis.close();
    }

    @Test
    public void testDownLoadImgFromDataBase() {
        try {
            //按id查询用户信息
            Userinfo userinfo = userinfoMapper.selectByPrimaryKey(7);
            //读取从数据库查到的用户头像
            byte[] origin = userinfo.getAvatar();
            System.out.println(origin);
            String str = new String(origin);
            System.out.println(str);
            byte[] now = str.getBytes();
            System.out.println(now);
//            byte[] bytes = JSONArray.parseObject(str, byte[].class);
//            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
//            BufferedImage read = ImageIO.read(bais);
//            //写出数据到C://22.png
//            ImageIO.write(read, "png", new FileOutputStream(new File("D://22.png")));
//            bais.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void query() {
        try{
            Userinfo userinfo = userinfoMapper.selectByPrimaryKey(4);
            byte[] a = userinfo.getAvatar();
            System.out.println(a);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void strtotime() {
        String str = "2021-05-21T16:31:04.000+00:00";
        System.out.println();
    }
}
