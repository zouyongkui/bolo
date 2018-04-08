package com.zyk.bolo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Tb_Community implements Serializable {
    @Id
    @GeneratedValue
    private String id;

    private Date createtime;

    private String userid;

    private Date replytime;

    private String title;

    private Byte istop;

    private Byte isonline;

    private String content;

    private Integer visitcount;

    private int floorsCount;


    public Tb_Community() {
    }

    public int getFloorsCount() {
        return floorsCount;
    }

    public void setFloorsCount(int floorsCount) {
        this.floorsCount = floorsCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Date getReplytime() {
        return replytime;
    }

    public void setReplytime(Date replytime) {
        this.replytime = replytime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Byte getIstop() {
        return istop;
    }

    public void setIstop(Byte istop) {
        this.istop = istop;
    }

    public Byte getIsonline() {
        return isonline;
    }

    public void setIsonline(Byte isonline) {
        this.isonline = isonline;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getVisitcount() {
        return visitcount;
    }

    public void setVisitcount(Integer visitcount) {
        this.visitcount = visitcount;
    }
}