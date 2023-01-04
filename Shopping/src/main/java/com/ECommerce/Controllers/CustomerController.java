package com.ECommerce.Controllers;

import com.ECommerce.DTO.LogInDTO;
import com.ECommerce.DTO.SignUpDTO;
import com.ECommerce.Exception.CustomerException;
import com.ECommerce.Exception.LogInException;
import com.ECommerce.Modules.Customers;
import com.ECommerce.Modules.LogInHistory;
import com.ECommerce.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService cs;

    @PostMapping("/SignUp")
    public ResponseEntity<Customers> signUpCustomer(@RequestBody SignUpDTO sign) throws CustomerException {
        Customers c1= cs.signUpCustomer(sign);

        return new ResponseEntity<Customers>(c1, HttpStatus.CREATED);
    }
    @PutMapping("/customer")
    public ResponseEntity<Customers> updateCustomer(@Valid @RequestBody Customers customer, @RequestParam(required = false) String key) throws CustomerException{
        Customers updatedCustomer= cs.updateCustomer(customer,key);

        return new ResponseEntity<Customers>(updatedCustomer,HttpStatus.OK);
    }
    @DeleteMapping("/DeleteCustomer")
    public ResponseEntity<Customers> removeCustomer(LogInDTO login) throws CustomerException{
        Customers customer= cs.removeCustomer(login);
        return new ResponseEntity<Customers>(customer,HttpStatus.OK);
    }
    @GetMapping("/customer/{id}")
    public ResponseEntity<Customers> viewCustomer(@PathVariable("id")String Id) throws CustomerException, LogInException {
        Customers customer= cs.viewCustomer(Id);

        return new ResponseEntity<Customers>(customer,HttpStatus.OK);
    }
    @GetMapping("/customer/logInHistories")
    public ResponseEntity<List<LogInHistory>> viewLogInHistory(@RequestParam(required = false) String Id) throws CustomerException{
        List<LogInHistory> logInHistories= cs.viewLogInHistory(Id);
        return new ResponseEntity<List<LogInHistory>>(logInHistories,HttpStatus.ACCEPTED);
    }

//8888888888888888888888888888888888888888888888888888888 Admin 8888888888888888888888888888888888888888888888888888888888888888888888888\\
    @GetMapping("/customers")
    public ResponseEntity<List<Customers>> viewAllCustomers(@RequestParam(required = false) String key) throws CustomerException{
        List<Customers> customer= cs.viewAllCustomers(key);

        return new ResponseEntity<List<Customers>>(customer,HttpStatus.OK);
    }



}
