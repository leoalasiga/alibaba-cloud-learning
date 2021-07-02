package com.alibaba.cloud.dubboprovidersample.sample;

import com.alibaba.cloud.EchoService;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author ljj
 * @description
 * @date 2021-06-30
 */
@Service
public class SampleEchoService implements EchoService {
    @Override
    public String echo(String message) {
        return "[ECHO] " + message;

    }
}
