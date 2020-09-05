package com.hackathon.bookmarkshorturl.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URL;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackathon.bookmarkshorturl.dto.LongUrlRequest;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UrlController.class)
class UrlControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
    protected ObjectMapper objectMapper;

	@Test
	void whenValidInput_thenReturns200() throws Exception {
		LongUrlRequest request = new LongUrlRequest(new URL("http://test.com"));
		
		mockMvc.perform(post("/api/v1/create-short-url")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.url").value("http://test.com"));
	}
}
