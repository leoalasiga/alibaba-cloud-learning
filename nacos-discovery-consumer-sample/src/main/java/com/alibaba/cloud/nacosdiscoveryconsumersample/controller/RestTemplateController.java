package com.alibaba.cloud.nacosdiscoveryconsumersample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author ljj
 * @description
 * @date 2021-06-28
 */
@RestController
public class RestTemplateController {
    @LoadBalanced
    @Autowired
    public RestTemplate restTemplate;

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/call/echo/{message}")
    public String callEcho(@PathVariable String message) {
        return restTemplate.getForObject("http://nacos-discovery-provider-sample/echo/" +
                message, String.class);
    }
}
