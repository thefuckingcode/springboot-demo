package com.example.springbootdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class TestBeanFactory {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // bean 的定义(class,scope，初始化，销毁)
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(Config.class).getBeanDefinition();
        beanFactory.registerBeanDefinition("config", beanDefinition);

        // 给BeanFactory添加一些后处理器
        AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);

        // BeanFactoryPostProcessor扩展BeanFactory的功能，比如能够处理@Bean注解的对象
        beanFactory.getBeansOfType(BeanFactoryPostProcessor.class).values().stream().sorted(beanFactory.getDependencyComparator()).forEach(value -> value.postProcessBeanFactory(beanFactory));

        // Bean 后处理器，针对bean的生命周期的各个阶段提供扩展功能，比如@Autowired、@Resource
        beanFactory.getBeansOfType(BeanPostProcessor.class).values().forEach(beanFactory::addBeanPostProcessor);

        // 提前创建单例对象，如果不调用该方法，则在使用获取bean时实例化bean
        beanFactory.preInstantiateSingletons();

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(beanFactory.getBean(Bean1.class).getBean2());


    }

    @Configuration
    static class Config {
        @Bean
        public Bean1 bean1() {
            return new Bean1();
        }

        @Bean
        public Bean2 bean2() {
            return new Bean2();
        }
    }

    static class Bean1 {
        public Bean1() {
            System.out.println("构建Bean1()");
        }

        @Autowired
        private Bean2 bean2;

        public Bean2 getBean2() {
            return bean2;
        }

        public void setBean2(Bean2 bean2) {
            this.bean2 = bean2;
        }
    }

    static class Bean2 {
        public Bean2() {
            System.out.println("构建Bean2()");
        }

        @Autowired
        private Bean1 bean1;

        public Bean1 getBean1() {
            return bean1;
        }

        public void setBean1(Bean1 bean1) {
            this.bean1 = bean1;
        }
    }
}