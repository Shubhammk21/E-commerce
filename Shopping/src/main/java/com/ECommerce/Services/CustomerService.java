package com.ECommerce.Services;

import com.ECommerce.DTO.LogInDTO;
import com.ECommerce.DTO.SignUpDTO;
import com.ECommerce.Exception.CustomerException;
import com.ECommerce.Exception.LogInException;
import com.ECommerce.Modules.Customers;

import java.util.List;

public interface CustomerService {

    public Customers signUpCustomer(SignUpDTO sign) throws CustomerException;

    public Customers updateCustomer(Customers cust,String key) throws CustomerException;

    public Customers removeCustomer(LogInDTO login) throws CustomerException;

    public Customers viewCustomer(String Id) throws CustomerException, LogInException;

    public List<Customers> viewAllCustomers(String key) throws CustomerException;

}
