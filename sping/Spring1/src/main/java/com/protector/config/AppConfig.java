package com.protector.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import com.protector.Spring1.Alien;
import com.protector.Spring1.Computer;
import com.protector.Spring1.Desktop;
import com.protector.Spring1.Laptop;

@Configuration
@ComponentScan("com.protector")
public class AppConfig {
	
//	@Bean
//	public Alien alien(@Qualifier("laptop") Computer computer) {
//		final Alien alien = new Alien();
//		alien.setAge(21);
//		alien.setComputer(computer);
//		return alien;
//	}
//	
////	@Bean(name="desktop")
//	@Bean
////	@Scope("prototype")
//	public Desktop desktop() {
//		return new Desktop();
//	}
//	
//	
//	@Bean
//	@Primary
//	public Laptop laptop() {
//		return new Laptop();
//	}

}
