package com.ECommerce.Controllers;

import com.ECommerce.DTO.SignUpDTO;
import com.ECommerce.Exception.CustomerException;
import com.ECommerce.Modules.Customers;
import com.ECommerce.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService cs;

    @PostMapping("/SignUp")
    public ResponseEntity<Customers> signUpCustomer(@RequestBody SignUpDTO sign) throws CustomerException {
        Customers c1= cs.signUpCustomer(sign);

        return new ResponseEntity<Customers>(c1, HttpStatus.CREATED);
    }
}
