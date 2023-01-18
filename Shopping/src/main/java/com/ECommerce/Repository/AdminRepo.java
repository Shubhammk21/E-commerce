package com.ECommerce.Repository;

import com.ECommerce.DTO.LogInDTO;
import com.ECommerce.Modules.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepo extends JpaRepository<Admin,String> {

    public Optional<Admin> findByEmail(String email);
}
