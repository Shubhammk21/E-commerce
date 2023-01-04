package com.ECommerce.Controllers;

import com.ECommerce.DTO.LogInDTO;
import com.ECommerce.Exception.CustomerException;
import com.ECommerce.Exception.LogInException;
import com.ECommerce.Modules.Admin;
import com.ECommerce.Modules.Customers;
import com.ECommerce.Services.LogInServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LogInController {
    @Autowired
    private LogInServices login;

    @PostMapping("Customers/Login")
    public ResponseEntity<Customers> logInCustomer(@RequestBody LogInDTO dto) throws LogInException, CustomerException{

        Customers customer=  login.logInCustomer(dto);

        return new ResponseEntity<Customers>(customer, HttpStatus.ACCEPTED);
    }
    @PostMapping("Customers/LogOut")
    public ResponseEntity<String> logOutCustomer(@RequestParam(required = false) String key) throws LogInException{
        String str= login.logOutCustomer(key);

        return new ResponseEntity<String>(str, HttpStatus.OK);
    }

//88888888888888888888888888888888888888888888888888888 Admin Features 8888888888888888888888888888888888888888888888888888888\\
    @PostMapping("/Admin/Login")
    public ResponseEntity<Admin> logInAdmin(@RequestBody LogInDTO dto) throws LogInException{

        Admin admin= login.logInAdmin(dto);

        return new ResponseEntity<Admin>(admin, HttpStatus.ACCEPTED);
    }
    @PostMapping("Admin/LogOut")
    public ResponseEntity<String> logOutAdmin(@RequestParam(required = false) String key) throws LogInException{
        String str= login.logOutAdmin(key);

        return new ResponseEntity<String>(str, HttpStatus.OK);
    }
}
