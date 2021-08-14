package com.example.demo.shipment.aop.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = {"com.example.demo.shipment.client"})
public class FeignConfiguration {
}
