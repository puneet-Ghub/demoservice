package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication

@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Autowired
    Configuration configuration;
	
	@GetMapping("/hello")
	public String hello(@RequestParam(required = false) String  name) {
		
		if(name!=null) {
			return name+","+configuration.getMessage()+", Server Time:"+new java.util.Date();
		}
		return configuration.getMessage()+" @ "+new java.util.Date();
	}
	
	
	
	
	@GetMapping("/hello2/{name}")
	public String hello2(@PathVariable String  name) {
		
		if(name!=null) {
			return "Hello "+name+", Server Time:"+new java.util.Date();
		}
		return "Hello World @ "+new java.util.Date();
	}
	
	
	
	@PostMapping("/hello")
	public String hello(@RequestParam(required = false) MessageToServer  message) {
		System.out.println("Message:"+message);
		if(message!=null) {
			return "Hello "+message.getName()+", Server Time:"+new java.util.Date();
		}
		return "Hello World @ "+new java.util.Date();
	}
	
	

}
