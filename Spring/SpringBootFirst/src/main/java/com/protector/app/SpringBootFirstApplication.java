package com.protector.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.protector.app.model.Alien;
import com.protector.app.model.Laptop;
import com.protector.app.service.LaptopService;

@SpringBootApplication
public class SpringBootFirstApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBootFirstApplication.class, args);
		
		
		LaptopService service = context.getBean(LaptopService.class);
		
		Laptop laptop = context.getBean(Laptop.class);
		service.addLaptop(laptop);
		
		
//		Alien obj = context.getBean(Alien.class);
//		System.out.println(obj.getAge());
//		obj.code();
		
	}

}
