package com.hackathon.bookmarkshorturl.service.iml;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hackathon.bookmarkshorturl.dto.UrlDto;
import com.hackathon.bookmarkshorturl.entity.ApiConstants;
import com.hackathon.bookmarkshorturl.entity.BsGroup;
import com.hackathon.bookmarkshorturl.entity.Url;
import com.hackathon.bookmarkshorturl.entity.User;
import com.hackathon.bookmarkshorturl.repository.UrlRepository;
import com.hackathon.bookmarkshorturl.service.BsGroupService;
import com.hackathon.bookmarkshorturl.service.UrlConversionService;
import com.hackathon.bookmarkshorturl.service.UrlService;
import com.hackathon.bookmarkshorturl.service.UserService;

@Service
public class UrlServiceImpl implements UrlService {

	@Autowired
    private UrlRepository urlRepository;
	
	@Autowired
    private UrlConversionService urlConversionService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BsGroupService bsGroupService;
	
    @Override
	public String convertToShortUrl(Url urlObj) {
    	urlObj.setCreator(this.userService.getUser());
    	var entity = this.urlRepository.save(urlObj);

        return this.urlConversionService.encode(entity.getId());
    }
    
    @Override
	public String getOriginalUrl(String pathName) {
        var id = this.urlConversionService.decode(pathName);
        var entity = urlRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no entity with " + pathName));

        if (entity.getCreator() == null && entity.getExpirationDateTime() != null && entity.getExpirationDateTime().isBefore(LocalDateTime.now())){
            this.urlRepository.delete(entity);
            throw new EntityNotFoundException(ApiConstants.LINKED_EXPIRED);
        }
        if(entity.getCreator() != null) {
        	throw new EntityNotFoundException(ApiConstants.INVALID_URL);
        }

        return entity.getLongUrl();
    }
    
    @Override
    public String getOriginalUrl(String groupname, String pathname) {
    	String actualGroupname;
    	String longUrl;
    	if(groupname.startsWith("f-")) {
    		actualGroupname = groupname.substring(groupname.indexOf("-")+1);
    		longUrl = this.getOriginalGroupUrl(ApiConstants.FEATURE, actualGroupname, pathname);
    	}else if(groupname.startsWith("t-")) {
    		actualGroupname = groupname.substring(groupname.indexOf("-")+1);
    		longUrl = this.getOriginalGroupUrl(ApiConstants.TRIBE, actualGroupname, pathname);
    	}else {
    		longUrl = this.getUserUrl(groupname, pathname);
    	}
    	if(longUrl != null) {
    		return longUrl;
    	}
    	throw new EntityNotFoundException(ApiConstants.INVALID_URL);
        
    }
    private String getUserUrl(String username, String pathname) {
    	var id = this.urlConversionService.decode(pathname);
        var entity = urlRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no entity with " + pathname));
        if (entity.getCreator().getName().equals(username)
        		&& entity.getExpirationDateTime() != null
        		&& entity.getExpirationDateTime().isBefore(LocalDateTime.now())){
            this.urlRepository.delete(entity);
            throw new EntityNotFoundException(ApiConstants.LINKED_EXPIRED);
        }
        
        if(!entity.getCreator().getName().equals(username)) {
        	throw new EntityNotFoundException(ApiConstants.INVALID_URL);
        }
        return entity.getLongUrl();
    }
    
    private String getOriginalGroupUrl(String type, String groupname, String pathname) {
    	BsGroup group = this.bsGroupService.getGroupsByName(groupname).get(0);
    	var id = this.urlConversionService.decode(pathname);
    	Url url = group.getUrls()
    			.stream()
    			.filter(u -> u.getId() == id).findFirst().orElseThrow();
    	if(url != null && group.getGroupType().equals(type)) {
    		return url.getLongUrl();
    	}
    	return null;
    }
    
    @Override
    public List<UrlDto> getUrls(){
    	final User user = this.userService.getUser();
    	Set<BsGroup> groups = this.bsGroupService.getGroupsByUser(user);
    	List<UrlDto> urlDtos = new ArrayList<>();
    	for(BsGroup group: groups) {
    		Set<Url> urls = group.getUrls();
    		for(Url url: urls) {
    			try {
    				if(group.getGroupType().equals(ApiConstants.TRIBE)) {
    					urlDtos.add(UrlDto.build(url).setShortUrl(new
							URL(ServletUriComponentsBuilder.fromCurrentContextPath().toUriString()+"/r/"+
									"t-"+group.getGroupName()+"/"+this.urlConversionService.encode(url.getId()))));
    				}else {
    					urlDtos.add(UrlDto.build(url).setShortUrl(new
    							URL(ServletUriComponentsBuilder.fromCurrentContextPath().toUriString()+"/r/"+
    									"f-"+group.getGroupName()+"/"+this.urlConversionService.encode(url.getId()))));
    				}

				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
    		}
    	}
    	
    	List<Url> urls = this.urlRepository.findByCreator(user);
    	List<UrlDto> usersUrl= urls.stream().map(url -> {
			try {
				return UrlDto.build(url).setShortUrl(new
						URL(ServletUriComponentsBuilder.fromCurrentContextPath().toUriString()+"/r/"+
								user.getName()+"/"+this.urlConversionService.encode(url.getId())));
			} catch (MalformedURLException e) {
				return null;
			}
		}).collect(Collectors.toList());
    	urlDtos.addAll(usersUrl);
    	return urlDtos;
    }
    
    @Override
    public void update(Url urlObj, String pathName) {
    	var id = this.urlConversionService.decode(pathName);
    	Url url = this.urlRepository.findById(id).orElseThrow();
    	url.setExpirationDateTime(urlObj.getExpirationDateTime());
    	url.setDescription(urlObj.getDescription());
    	url.setShortTitle(urlObj.getShortTitle());
    	if(urlObj.getFileName() != null) {
    		url.setFileName(urlObj.getFileName());
    	}
    	if(urlObj.getFileType() != null) {
    		url.setFileType(urlObj.getFileType());
    	}
    	if(urlObj.getFaviconData() != null && urlObj.getFaviconData().length != 0) {
    		url.setFaviconData(urlObj.getFaviconData());
    	}
    	this.urlRepository.save(url);
    }
    
    @Override
    public void delete(String pathName) {
    	var id = this.urlConversionService.decode(pathName);
    	this.urlRepository.deleteById(id);
    }
    
    @Override
    public Url getUrlById(long id) {
    	return this.urlRepository.findById(id).orElseThrow();
    }
    
    @Override
    public void save(Url url) {
    	this.urlRepository.save(url);
    }
    
}