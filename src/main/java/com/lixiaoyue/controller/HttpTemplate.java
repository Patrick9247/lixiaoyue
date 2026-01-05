package com.lixiaoyue.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public class HttpTemplate {

    private static String KEY = "f90c0aec426e104bacc277c2760350bc";

    public static String httpGet(String url) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(url, HttpMethod.GET, null, String.class).getBody();
    }

    public static String httpPost(String url, String name) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json; charset=utf-8");
        headers.set("Accept", "application/json");
        headers.set("Ak", KEY);
        return restTemplate.postForEntity(url, name, String.class).getBody();
    }

    public static void main(String[] str) {
        System.out.println(HttpTemplate.httpGet("https://www.example.com"));
        System.out.println(HttpTemplate.httpPost("https://www.example.com", "ming"));
    }
}