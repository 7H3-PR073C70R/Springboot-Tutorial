package com.protector.Spring1;

import org.springframework.stereotype.Component;

@Component
public class Laptop implements Computer {
	
	public Laptop() {
		System.out.println("Laptop Object Created");
	}

	@Override
	public void compile() {
		System.out.println("Compiling using Laptop");
	}

}
