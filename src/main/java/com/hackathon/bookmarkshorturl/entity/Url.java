package com.hackathon.bookmarkshorturl.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "url")
public class Url {

    @Id
    private long id = System.currentTimeMillis()-1599556275803l;

    @Column(nullable = false)
    private String longUrl;

    @Column(nullable = false)
    private Date createdDate;

    private Date expiresDate;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getExpiresDate() {
        return expiresDate;
    }

    public void setExpiresDate(Date expireDate) {
        this.expiresDate = expireDate;
    }
}