package com.study.config;

import com.study.filter.MyfilterOne;
import com.study.interceptor.MyInterceptor;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

@Configuration
public class Config implements WebMvcConfigurer {

    // 设置字符集同时保证回传的字符不出现乱码
    @Bean
    public RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }

    // 注册es客户端  2019-12-02
    @Bean
    public TransportClient client() throws UnknownHostException {
        // 指定群名，默认为elasticsearch，如果改了集群名，这里一定要加
        Settings settings = Settings.builder()
                .put("cluster.name", "elasticsearch")
                .build();
        TransportClient client = new PreBuiltTransportClient(settings);
        /**
         * ES的TCP端口为9300,而不是之前练习的HTTP端口9200
         *  这里只配置了一个节点的地址然添加进去,也可以配置多个从节点添加进去再返回
         */
        InetSocketTransportAddress node = new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300);

        client.addTransportAddress(node);
        return client;
    }

    // 过滤器-方式一 注册自定义filter    2019-12-05
    @Bean
    public FilterRegistrationBean registrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new MyfilterOne());
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

    // 配置拦截器：实现WebMvcConfigurer接口的方法
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor());
    }
}
