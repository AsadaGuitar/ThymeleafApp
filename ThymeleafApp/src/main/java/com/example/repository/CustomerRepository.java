package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	@Query("select distinct x from Customer x join fetch x.user order by x.firstName, x.lastName")
	List<Customer> findAllWithUserOrderByName();
}
