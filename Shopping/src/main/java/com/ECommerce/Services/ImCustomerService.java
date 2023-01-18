package com.ECommerce.Services;

import java.time.LocalDateTime;
import java.util.*;

import com.ECommerce.DTO.LogInDTO;
import com.ECommerce.Modules.Admin;
import com.ECommerce.Modules.CustomerActive;
import com.ECommerce.Modules.LogInHistory;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ECommerce.DTO.SignUpDTO;
import com.ECommerce.Exception.CustomerException;
import com.ECommerce.Exception.LogInException;
import com.ECommerce.Modules.Customers;
import com.ECommerce.Repository.CustomerActiveRepo;
import com.ECommerce.Repository.CustomerRepo;
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
        List<LogInHistory> stack= new ArrayList<>();
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
            String key= RandomString.make(16);
            Customers c3= new Customers(sign.getFirstName(),sign.getLastName(),sign.getUsername(),
                    null,sign.getPassword(),sign.getDob(),sign.getGender());
            checkHistoryLength(c3.getHistory());
            c3.getHistory().add(0,new LogInHistory(c3.getMobileNumber(),c3.getPassword(),LocalDateTime.now(),null,null));
            cd.save(c3);
            c3.setCustomerActive(new CustomerActive(c3.getCustomerId(),c3.getPassword(),"Customer",key, LocalDateTime.now()));
            return cd.save(c3);

        }
        else if(!validateEmail(sign.getUsername())){
            throw new CustomerException("Invalid Email Format");
        }
        else{
            String key= RandomString.make(16);
            Customers c3= new Customers(sign.getFirstName(),sign.getLastName(),null,
                    sign.getUsername(),sign.getPassword(),sign.getDob(),sign.getGender());
            checkHistoryLength(c3.getHistory());
            c3.getHistory().add(0,new LogInHistory(c3.getEmail(),c3.getPassword(),LocalDateTime.now(),null,null));
            cd.save(c3);
            c3.setCustomerActive(new CustomerActive(c3.getCustomerId(),c3.getPassword(),"Customer",key, LocalDateTime.now()));

            return cd.save(c3);
        }
    }
    @Override
    public Customers updateCustomer(Customers customer, String key) throws CustomerException {

        Optional<CustomerActive> u1= cr.findByUuId(key);

        if(u1.isEmpty()) {
            throw new CustomerException("♣█☻ Invalid Entry ☻█♣");
        }else {
            Optional<Customers> c1= cd.findByCustomerId(u1.get().getUserId());
            if(c1.isEmpty()){
                throw new CustomerException("♣█☻ Invalid Entry please login ☻█♣");
            }else {
                customer.setCustomerId(u1.get().getUserId());
                return cd.save(customer);
            }
        }


    }

    @Override
    public Customers removeCustomer(LogInDTO login) throws CustomerException {

        Optional<Customers> customerWithEmail= cd.findByEmail(login.getUsername());
        Optional<Customers> customerWithPhone= cd.findByMobileNumber(login.getUsername());

        if(customerWithPhone.isEmpty() && customerWithEmail.isEmpty()){
            throw new CustomerException("Username is invalid");
        }
        else if (customerWithPhone.isPresent()){
            if (login.getPassword().equals(customerWithPhone.get().getPassword())){
                cd.delete(customerWithPhone.get());
                return customerWithPhone.get();
            }else {
                throw new CustomerException("Incorrect Password");
            }
        }
        else {
            if (!login.getPassword().equals(customerWithEmail.get().getPassword())){
                throw new CustomerException("Incorrect Password");
            }else {
                cd.delete(customerWithPhone.get());
                return customerWithPhone.get();
            }
        }
    }

    @Override
    public Customers viewCustomer(String Id) throws CustomerException, LogInException {
        return cd.findByCustomerId(Id).orElseThrow(()-> new CustomerException("♣█☻ Login to view Account ☻█♣"));
    }

    @Override
    public List<LogInHistory> viewLogInHistory(String id) throws CustomerException{
        Optional<Customers> customer= cd.findByCustomerId(id);
        if (customer.isEmpty()){
            throw new CustomerException("No data there");
        }else{
            return customer.get().getHistory();
        }
    }

    public boolean validateEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +"[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
    public void checkHistoryLength(List<LogInHistory> customerActives){
        if (customerActives.size()==5) {
            customerActives.remove(4);
        }
    }


//************************************************************Admin Feature*******************************************************************************************//
    @Override
    public List<Customers> viewAllCustomers(String key) throws CustomerException{

        Optional<CustomerActive> customerActive= cr.findByUuId(key);
        if(customerActive.isEmpty()){
            throw new CustomerException("♣█☻ Invalid Entry ☻█♣");
        } else if (!customerActive.get().getRole().equalsIgnoreCase("Admin")) {
            throw new CustomerException("♣█☻ You Are Not Admin ☻█♣");
        }else{
            return cd.findAll();
        }
    }

}
