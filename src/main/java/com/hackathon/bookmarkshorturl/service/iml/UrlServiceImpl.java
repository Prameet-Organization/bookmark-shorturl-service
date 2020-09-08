package com.hackathon.bookmarkshorturl.service.iml;

import java.util.Date;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackathon.bookmarkshorturl.dto.UrlRequest;
import com.hackathon.bookmarkshorturl.entity.Url;
import com.hackathon.bookmarkshorturl.repository.UrlRepository;
import com.hackathon.bookmarkshorturl.service.UrlConversionService;
import com.hackathon.bookmarkshorturl.service.UrlService;

@Service
public class UrlServiceImpl implements UrlService {

	@Autowired
    private UrlRepository urlRepository;
	
	@Autowired
    private UrlConversionService urlConversionService;

    @Override
	public String convertToShortUrl(UrlRequest request) {
        var url = new Url();
        url.setLongUrl(request.getUrl().toString());
        url.setCreatedDate(new Date());
        var entity = this.urlRepository.save(url);

        return this.urlConversionService.encode(entity.getId());
    }
    
    @Override
	public String getOriginalUrl(String pathName) {
        var id = this.urlConversionService.decode(pathName);
        var entity = urlRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no entity with " + pathName));

        if (entity.getExpiresDate() != null && entity.getExpiresDate().before(new Date())){
            this.urlRepository.delete(entity);
            throw new EntityNotFoundException("Link expired!");
        }

        return entity.getLongUrl();
    }
}