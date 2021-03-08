package com.study;

import com.study.listener.ApplicationEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@EnableAsync
@EnableTransactionManagement
@SpringBootApplication
@ServletComponentScan
public class StudyApplication {

    private Logger logger = LoggerFactory.getLogger(ApplicationEventListener.class);

    public static void main(String[] args) {

        // 第二种增加监听的方式
//        SpringApplication application = new SpringApplication(StudyApplication.class);
//        application.addListeners(new ApplicationEventListener());
//        application.run(args);

        SpringApplication.run(StudyApplication.class, args);
    }


//    @Bean
//    //设置字符集同时保证回传的字符不出现乱码
//    public RestTemplate getRestTemplate(){
//        RestTemplate restTemplate =  new RestTemplate(new HttpComponentsClientHttpRequestFactory());
//        restTemplate.getMessageConverters().set(1,new StringHttpMessageConverter(StandardCharsets.UTF_8));
//        return restTemplate;
//    }


    /**
     * kafka监听topic:test
     * 测试 kafka 2020-06-16
     * @param s
     */
//    @KafkaListener(topics = "test")
//    public void test(String s) {
//        logger.info("kafka消费到的数据：{}", s);
//    }

}
