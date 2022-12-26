package com.ECommerce.Services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ECommerce.DTO.SignUpDTO;
import com.ECommerce.Exception.CustomerException;
import com.ECommerce.Exception.LogInException;
import com.ECommerce.Modules.Customers;
import com.ECommerce.Repository.CustomerActiveRepo;
import com.ECommerce.Repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class ImCustomerService implements CustomerService{


    @Autowired
    private CustomerRepo cd;

    @Autowired
    private CustomerActiveRepo cr;

    @Override
    public Customers signUpCustomer(SignUpDTO sign) throws CustomerException {

        Optional<Customers>c1= cd.findByMobileNumber(sign.getUsername());
        Optional<Customers>c2= cd.findByEmail(sign.getUsername());
        long num;
        try {
            num=Long.parseLong(sign.getUsername());
        }catch (NumberFormatException e){
            num=0;
        }

        if (c1.isPresent() || c2.isPresent()) {
            throw new CustomerException("♣█☻ Already record there ☻█♣");
        }
        else if(num>=6000000000L){
            Customers c3= new Customers(sign.getFirstName(),sign.getLastName(),sign.getUsername(),
                    null,sign.getPassword(),sign.getDob(),sign.getGender());

            return cd.save(c3);

        }
        else if(!validateEmail(sign.getUsername())){
            throw new CustomerException("Invalid Email Format");
        }
        else{
            Customers c3= new Customers(sign.getFirstName(),sign.getLastName(),null,
                    sign.getUsername(),sign.getPassword(),sign.getDob(),sign.getGender());

            return cd.save(c3);
        }
    }
//    @Override
//    public Customers updateCustomer(Customers cust, String key) throws CustomerException {
//        return null;
//    }
//
//    @Override
//    public Customers removeCustomer(Customers cust) throws CustomerException {
//        return null;
//    }
//
//    @Override
//    public Customers viewCustomer(String Id) throws CustomerException, LogInException {
//        return null;
//    }
//
//    @Override
//    public List<Customers> viewAllCustomers(String key) throws CustomerException, LogInException {
//        return null;
//    }
    public boolean validateEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +"[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

}
