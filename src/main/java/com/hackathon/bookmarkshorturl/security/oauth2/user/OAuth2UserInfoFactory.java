package com.hackathon.bookmarkshorturl.security.oauth2.user;

import java.util.Map;

import com.hackathon.bookmarkshorturl.entity.AuthProvider;
import com.hackathon.bookmarkshorturl.exception.OAuth2AuthenticationProcessingException;

public class OAuth2UserInfoFactory {
	
	private OAuth2UserInfoFactory() {
	}

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
       if (registrationId.equalsIgnoreCase(AuthProvider.github.toString())) {
            return new GithubOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}
