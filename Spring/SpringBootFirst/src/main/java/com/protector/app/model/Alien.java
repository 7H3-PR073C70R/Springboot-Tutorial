package com.protector.app.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Alien {
	
	private int age;
	private Computer computer;
	
	public void code() {
		computer.compile();
		System.out.println("Coding");
	}

	public int getAge() {
		return age;
	}
	
	@Value("25")
	public void setAge(int age) {
		this.age = age;
	}
	
	public Computer getComputer() {
		return computer;
	}
	
	@Autowired
	@Qualifier("laptop")
	public void setComputer(Computer computer) {
		this.computer = computer;
	}

}
