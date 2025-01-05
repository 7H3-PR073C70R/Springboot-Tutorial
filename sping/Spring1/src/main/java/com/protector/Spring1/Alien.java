package com.protector.Spring1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class Alien {
	
	private int age;
	private Computer computer;
	
	public Alien() {}
	
	
	public Alien(int age, Computer computer) {
		this.age = age;
		this.computer = computer;
	}

	public int getAge() {
		return age;
	}
	
	@Value("21")
	public void setAge(int age) {
		this.age = age;
	}
	
	public Computer getComputer() {
		return computer;
	}
	
	@Autowired
	@Qualifier("laptop")
	public void setComputer(Computer laptop) {
		this.computer = laptop;
	}
	
	public void code() {
		getComputer().compile();
		System.out.println("Coding");
		
	}
}
