package com.volcano.examonlineserv.utils;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImageLoader {

    /**
     * 文件名后缀前添加一个时间戳
     */
    public static String getFileName(String fileName) {
        int index = fileName.lastIndexOf(".");
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyymmddHHmmss");  //设置时间格式
        String nowTimeStr = sdf.format(new Date()); // 当前时间
        fileName = fileName.substring(0, index) + "_" + nowTimeStr + fileName.substring(index);
        return fileName;
    }

    /**
     * 获取当前系统路径
     */
    public static String getImgPath() {
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(!path.exists()) {
            path = new File("");
        }
        File upload =  new File(path.getAbsolutePath(), "static/upload/");
        if(!upload.exists()) {
            upload.mkdirs();
        }
        return upload.getAbsolutePath();
    }
}
