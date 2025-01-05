package com.protector.app.repository;

import org.springframework.stereotype.Repository;

import com.protector.app.model.Laptop;

@Repository
public class LaptopRepository {
   public void save(Laptop laptop) {
	   System.out.println("Saved in DB...");
   }
}
