package com.hackathon.bookmarkshorturl.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "url")
public class Url {

    @Id
    private long id = System.currentTimeMillis()-1599556275803l;

    @Column(nullable = false, length = 4000, updatable = false)
    private String longUrl;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDateTime;

    private LocalDateTime expirationDateTime;
    
	private String shortTitle;
	
	@Column(length = 4000)
	private String description;
	
	private String faviconFileName;

    private String faviconFileType;
    
    @OneToOne
    private User creator;

    @Lob
    private byte[] faviconData;


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

	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public LocalDateTime getExpirationDateTime() {
		return expirationDateTime;
	}

	public void setExpirationDateTime(LocalDateTime expirationDateTime) {
		this.expirationDateTime = expirationDateTime;
	}

	public String getShortTitle() {
		return shortTitle;
	}

	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFileName() {
		return faviconFileName;
	}

	public void setFileName(String fileName) {
		this.faviconFileName = fileName;
	}

	public String getFileType() {
		return faviconFileType;
	}

	public void setFileType(String fileType) {
		this.faviconFileType = fileType;
	}

	public byte[] getFaviconData() {
		return faviconData;
	}

	public void setFaviconData(byte[] faviconData) {
		this.faviconData = faviconData;
	}

	public String getFaviconFileName() {
		return faviconFileName;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}	
	
	

}