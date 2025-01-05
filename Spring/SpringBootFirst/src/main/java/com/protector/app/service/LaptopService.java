package com.protector.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.protector.app.model.Laptop;
import com.protector.app.repository.LaptopRepository;

@Service
public class LaptopService {
	
	
	private LaptopRepository laptopRespositoy;
	
	public void addLaptop(Laptop laptop) {
		laptopRespositoy.save(laptop);
	}
	
	public boolean isGoodForProgramming() {
		return true;
	}
	
	@Autowired
	public void setLaptopRepository(LaptopRepository laptopRespositoy) {
		this.laptopRespositoy = laptopRespositoy;
	}
}
