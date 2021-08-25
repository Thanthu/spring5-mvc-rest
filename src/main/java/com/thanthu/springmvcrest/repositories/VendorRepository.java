package com.thanthu.springmvcrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thanthu.springmvcrest.domain.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long> {

}
