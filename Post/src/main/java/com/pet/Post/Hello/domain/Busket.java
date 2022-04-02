package com.pet.Post.Hello.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Busket {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private Long userid;
    private Long staffid;
    public Busket(){

    }
    public Busket(Long id, Long userid, Long staffid) {
        this.id = id;
        this.userid = userid;
        this.staffid = staffid;
    }

    public Long getUserId() {
        return userid;
    }

    public void setUserId(Long userId) {
        userid = userId;
    }

    public Long getStaffid() {
        return staffid;
    }

    public void setStaffid(Long staffid) {
        this.staffid = staffid;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
