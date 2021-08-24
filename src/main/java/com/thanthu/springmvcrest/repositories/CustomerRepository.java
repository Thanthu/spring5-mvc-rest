package com.thanthu.springmvcrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thanthu.springmvcrest.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
