package com.ECommerce.Repository;

import com.ECommerce.Modules.CustomerActive;
import com.ECommerce.Modules.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerActiveRepo extends JpaRepository<CustomerActive,String> {



}
