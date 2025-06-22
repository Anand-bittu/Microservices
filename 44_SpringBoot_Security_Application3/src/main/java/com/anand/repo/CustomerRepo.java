package com.anand.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.anand.entity.Customer;

public interface CustomerRepo  extends JpaRepository<Customer,Integer>{

	
	public Customer findByEmail(String email);
}
