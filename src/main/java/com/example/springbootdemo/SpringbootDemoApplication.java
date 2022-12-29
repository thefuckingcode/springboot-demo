package com.example.springbootdemo;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;

import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Map;

@SpringBootApplication
public class SpringbootDemoApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(SpringbootDemoApplication.class, args);

//        Field singletonObjects = DefaultSingletonBeanRegistry.class.getDeclaredField("singletonObjects");
//        singletonObjects.setAccessible(true);
//
//        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
//
//
//        Map<String, Object> map = (Map<String, Object>) singletonObjects.get(beanFactory);
//
//        map.forEach((key, value) -> {
//            System.out.println(key + "->" + value.toString());
//        });
//
//        System.out.println(context.getMessage("devops.app.version.id.null", null, Locale.ENGLISH));
//
//        Resource[] resources = context.getResources("classpath:application.yml");
//        for (Resource resource : resources) {
//            System.out.println(resource.exists());
//            System.out.println(IOUtils.toString(resource.getInputStream(), Charset.defaultCharset()));
//            System.out.println(resource);
//            System.out.println("=========");
//        }

//        System.out.println(context.getEnvironment().getProperty("PATH"));

        context.publishEvent(new UserRegisteredEvent(context));
    }

}
