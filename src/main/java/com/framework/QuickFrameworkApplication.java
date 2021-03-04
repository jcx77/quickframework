package com.framework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration;
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration;
import org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class, JdbcTemplateAutoConfiguration.class, RedisAutoConfiguration.class, RedisReactiveAutoConfiguration.class, RedisRepositoriesAutoConfiguration.class, JmxAutoConfiguration.class, WebSocketServletAutoConfiguration.class, CacheAutoConfiguration.class, TaskSchedulingAutoConfiguration.class, TaskExecutionAutoConfiguration.class, RestTemplateAutoConfiguration.class, OAuth2ResourceServerAutoConfiguration.class, JacksonAutoConfiguration.class, FreeMarkerAutoConfiguration.class, HttpEncodingAutoConfiguration.class })
//@Configurable //允许基于注解的配置
public class QuickFrameworkApplication {
//    /**
//     * 配置文件上传大小
//     */
//    @Bean
//    public MultipartConfigElement getMultiConfig() {
//        MultipartConfigFactory factory = new MultipartConfigFactory();
//        factory.setMaxFileSize("4000MB");
//        factory.setMaxRequestSize("4000MB");
//        return factory.createMultipartConfig();
//    }


    public static void main(String[] args) {
        SpringApplication.run(QuickFrameworkApplication.class, args);
    }

}
