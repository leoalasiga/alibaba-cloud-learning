package com.alibaba.cloud.nacosdiscoveryprovidersample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ljj
 * @description
 * @date 2021-06-28
 */
@RestController
public class ServiceController {

    @GetMapping("/echo/{message}")
    public String echo(@PathVariable String message) {
        return "[ECHO]:" + message;
    }
}
