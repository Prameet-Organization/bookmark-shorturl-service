package com.hackathon.bookmarkshorturl.service.iml;

import org.springframework.stereotype.Service;

import com.hackathon.bookmarkshorturl.service.UrlConversionService;

@Service
public class UrlConversionServiceImpl implements UrlConversionService {

    private static final String ALLOWED_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private char[] allowedCharacters = ALLOWED_STRING.toCharArray();
    private int base = allowedCharacters.length;

    @Override
	public String encode(long input){
        var encodedString = new StringBuilder();

        if(input == 0) {
            return String.valueOf(this.allowedCharacters[0]);
        }

        while (input > 0) {
            encodedString.append(this.allowedCharacters[(int) (input % this.base)]);
            input = input / this.base;
        }

        return encodedString.reverse().toString();
    }

    @Override
	public long decode(String input) {
        var characters = input.toCharArray();
        var length = characters.length;

        var decoded = 0;

        //counter is used to avoid reversing input string
        var counter = 1;
        for (int i = 0; i < length; i++) {
            decoded += ALLOWED_STRING.indexOf(characters[i]) * Math.pow(this.base, length - counter);
            counter++;
        }
        return decoded;
    }
}
