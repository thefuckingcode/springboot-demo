package com.example.springbootdemo;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Component1 {

    @EventListener
    public void subscribeEvent(UserRegisteredEvent userRegisteredEvent) {
        System.out.println("收到事件");
    }
}
