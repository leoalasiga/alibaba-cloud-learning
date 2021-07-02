package com.alibaba.cloud.nacosdiscoveryconsumersample.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author ljj
 * @description
 * @date 2021-06-28
 */

@FeignClient("nacos-discovery-provider-sample") // 指向服务提供者应用
public interface EchoService {

    @GetMapping("/echo/{message}")
    String echo(@PathVariable("message") String message);
}

