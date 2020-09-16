package com.hackathon.bookmarkshorturl.service.iml;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hackathon.bookmarkshorturl.dto.UrlDto;
import com.hackathon.bookmarkshorturl.entity.Url;
import com.hackathon.bookmarkshorturl.entity.User;
import com.hackathon.bookmarkshorturl.repository.UrlRepository;
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
            throw new EntityNotFoundException("Link expired!");
        }
        if(entity.getCreator() != null) {
        	throw new EntityNotFoundException("Invalid Url!");
        }

        return entity.getLongUrl();
    }
    
    @Override
    public String getOriginalUrl(String username, String pathName) {
        var id = this.urlConversionService.decode(pathName);
        var entity = urlRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no entity with " + pathName));
        if (entity.getCreator().getName().equals(username)
        		&& entity.getExpirationDateTime() != null
        		&& entity.getExpirationDateTime().isBefore(LocalDateTime.now())){
            this.urlRepository.delete(entity);
            throw new EntityNotFoundException("Link expired!");
        }
        
        if(!entity.getCreator().getName().equals(username)) {
        	throw new EntityNotFoundException("Invalid Url!");
        }
        return entity.getLongUrl();
    }
    
    @Override
    public List<UrlDto> getUrls(){
    	final User user = this.userService.getUser();
    	List<Url> urls = this.urlRepository.findByCreator(user);
    	return urls.stream().map(url -> {
			try {
				return UrlDto.build(url).setShortUrl(new
						URL(ServletUriComponentsBuilder.fromCurrentContextPath().toUriString()+"/"+
								user.getName()+"/"+this.urlConversionService.encode(url.getId())));
			} catch (MalformedURLException e) {
				return null;
			}
		}).collect(Collectors.toList());
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
    
}