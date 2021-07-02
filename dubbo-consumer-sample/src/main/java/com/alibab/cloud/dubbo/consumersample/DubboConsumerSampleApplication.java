package com.alibab.cloud.dubbo.consumersample;

import com.alibaba.cloud.EchoService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DubboConsumerSampleApplication {

    @Reference
    private EchoService echoService;

    @GetMapping("/echo")
    public String echo(String message) {
        return echoService.echo(message);
    }



    public static void main(String[] args) {
        SpringApplication.run(DubboConsumerSampleApplication.class, args);
    }

}
