package com.volcano.examonlineserv.bean;

import java.util.Date;

public class ArticleInfo {
    private Integer id;

    private Integer userid;

    private String username;

    private String title;

    private String description;

    private String field;

    private String img;

    private Integer commentnums;

    private Integer zannums;

    private Date createat;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field == null ? null : field.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public Integer getCommentnums() {
        return commentnums;
    }

    public void setCommentnums(Integer commentnums) {
        this.commentnums = commentnums;
    }

    public Integer getZannums() {
        return zannums;
    }

    public void setZannums(Integer zannums) {
        this.zannums = zannums;
    }

    public Date getCreateat() {
        return createat;
    }

    public void setCreateat(Date createat) {
        this.createat = createat;
    }
}