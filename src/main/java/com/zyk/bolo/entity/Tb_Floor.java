package com.zyk.bolo.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Tb_Floor implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    private Date createtime;

    private String userid;

    private String flcontent;

    private Byte isonline;

    private String communityid;

    public Tb_Floor() {
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

    public String getFlcontent() {
        return flcontent;
    }

    public void setFlcontent(String flcontent) {
        this.flcontent = flcontent;
    }

    public Byte getIsonline() {
        return isonline;
    }

    public void setIsonline(Byte isonline) {
        this.isonline = isonline;
    }

    public String getCommunityid() {
        return communityid;
    }

    public void setCommunityid(String communityid) {
        this.communityid = communityid;
    }
}