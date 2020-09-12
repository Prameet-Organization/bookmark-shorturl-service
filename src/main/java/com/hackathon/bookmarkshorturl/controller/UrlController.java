package com.hackathon.bookmarkshorturl.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;

import com.hackathon.bookmarkshorturl.dto.UrlDto;
import com.hackathon.bookmarkshorturl.dto.UrlResponse;
import com.hackathon.bookmarkshorturl.entity.Url;
import com.hackathon.bookmarkshorturl.service.UrlService;

@RestController
//@CrossOrigin(origins = "http://localhost:4200", 
	//methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS, RequestMethod.PATCH})
public class UrlController { 

	@Autowired
	private UrlService urlService;

	@PostMapping("/api/v1/short-urls")
	public ResponseEntity<UrlResponse> createShortUrl(@RequestParam(value="favicon", required = false) MultipartFile favicon,
					URL url,
					@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
					LocalDateTime expirationDateTime,
					String shortTitle,
					String description
					) throws IOException {
		
		  if(url != null) { 
			  Url urlObj = new Url();
			  urlObj.setLongUrl(url.toString());
			  urlObj.setExpirationDateTime(expirationDateTime);
			  urlObj.setCreatedDateTime(LocalDateTime.now());
			  urlObj.setShortTitle(shortTitle);
			  urlObj.setDescription(description);
			  if(favicon != null) {
				  urlObj.setFileName(favicon.getOriginalFilename());
				  urlObj.setFileType(favicon.getContentType());
				  urlObj.setFaviconData(favicon.getBytes());
			  }
			  var shortUrl =
			  this.urlService.convertToShortUrl(urlObj); var entity = new UrlResponse( new
			  URL(ServletUriComponentsBuilder.fromCurrentContextPath().toUriString()+"/"+
			  shortUrl) ); return new ResponseEntity<>(entity, HttpStatus.OK); 
		  }
			 

		return new ResponseEntity<>(new UrlResponse(), HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/{pathName}")
	public RedirectView getOriginalUrl(@PathVariable String pathName){
		return new RedirectView(this.urlService.getOriginalUrl(pathName));
	}
	
	@GetMapping("/api/v1/short-urls")
	public ResponseEntity<List<UrlDto>> getUrls(){
		return new ResponseEntity<>(this.urlService.getUrls(), HttpStatus.OK);
	}
	
	@PutMapping("/api/v1/short-urls/{pathName}")
	public ResponseEntity<Void> updateShortUrl(@PathVariable String pathName,
			@RequestParam(value="favicon", required = false) MultipartFile favicon,
					@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
					LocalDateTime expirationDateTime,
					String shortTitle,
					String description
					) throws IOException {
		
		  if(pathName != null) { 
			  Url urlObj = new Url();
			  urlObj.setExpirationDateTime(expirationDateTime);
			  urlObj.setShortTitle(shortTitle);
			  urlObj.setDescription(description);
			  if(favicon != null) {
				  urlObj.setFileName(favicon.getOriginalFilename());
				  urlObj.setFileType(favicon.getContentType());
				  urlObj.setFaviconData(favicon.getBytes());
			  }
				  
			  this.urlService.update(urlObj, pathName);
			  
		  }
		  return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
	}
	
	@DeleteMapping("/api/v1/short-urls/{pathName}")
	public ResponseEntity<Void> deleteShortUrl(@PathVariable String pathName){
		this.urlService.delete(pathName);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
	}


}
