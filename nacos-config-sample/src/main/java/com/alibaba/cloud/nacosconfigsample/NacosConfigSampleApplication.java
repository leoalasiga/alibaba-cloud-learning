package com.alibaba.cloud.nacosconfigsample;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.cloud.nacosconfigsample.entity.User;
import com.alibaba.nacos.api.config.listener.AbstractListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

@SpringBootApplication
@RestController
@RefreshScope
@EnableConfigurationProperties(User.class)
public class NacosConfigSampleApplication {

    @Autowired
    private User user;

    @Value("${user.name}")
    private String name;

    @Value("${user.age}")
    private int age;

    @Autowired
    private NacosConfigManager nacosConfigManager;


    @Bean
    public ApplicationRunner runner() {
        return args -> {
            String dataId = "nacos-config-sample.properties";
            String group = "TEST_GROUP";
            String namespace = "42a98d04-45a4-4246-82d7-b176c19c86da";
            nacosConfigManager.getConfigService().addListener(dataId, group, new AbstractListener() {
                @Override
                public void receiveConfigInfo(String configInfo) {
                    System.out.println("[Listener]" + configInfo);
                    System.out.println("[Before user]"+user);
                    Properties properties = new Properties();

                    try {
                        properties.load(new StringReader(configInfo));
                        String name = properties.getProperty("user.name");
                        int age = Integer.valueOf(properties.getProperty("user.age"));
                        user.setAge(age);
                        user.setName(name);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    System.out.println("[After user]"+user);
                }
            });
        };
    }

    @GetMapping("/hello")
    public String hello() {
        return String.format("[HTTP] user name: %s , user age: %d%n", name, age);
    }

    @GetMapping("/user")
    public String user() {
        return "[HTTP]" + user;
    }

    @PostConstruct
    public void init() {
        System.out.printf("[init] user name: %s , user age: %d%n", name, age);
    }

    @PreDestroy
    public void destory() {
        System.out.printf("[destory] user name: %s , user age: %d%n", name, age);
    }


    public static void main(String[] args) {
        SpringApplication.run(NacosConfigSampleApplication.class, args);
    }

}
