package com.ECommerce.Repository;

import com.ECommerce.Modules.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customers,String> {

    public Optional<Customers> findByMobileNumber(String userID);

    public Optional<Customers> findByEmail(String email);

}
