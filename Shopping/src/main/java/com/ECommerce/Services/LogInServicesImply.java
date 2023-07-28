package com.ECommerce.Services;

import com.ECommerce.DTO.LogInDTO;
import com.ECommerce.Exception.CustomerException;
import com.ECommerce.Exception.LogInException;
import com.ECommerce.Modules.Admin;
import com.ECommerce.Modules.CustomerActive;
import com.ECommerce.Modules.Customers;
import com.ECommerce.Modules.LogInHistory;
import com.ECommerce.Repository.AdminRepo;
import com.ECommerce.Repository.CustomerActiveRepo;
import com.ECommerce.Repository.CustomerRepo;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LogInServicesImply implements LogInServices{
    @Autowired
    private CustomerRepo cDao;

    @Autowired
    private CustomerActiveRepo caDao;
    @Autowired
    private AdminRepo aDao;

//    @Autowired
//    private LogInHistory logInHistory;

    @Override
    @Transactional
    public Customers logInCustomer(LogInDTO dto) throws LogInException, CustomerException {
        Optional<Customers> customerWithEmail= cDao.findByEmail(dto.getUsername());
        Optional<Customers> customerWithPhone= cDao.findByMobileNumber(dto.getUsername());


        if(customerWithPhone.isEmpty() && customerWithEmail.isEmpty()){
            throw new LogInException("Username is invalid");
        }

        else if (customerWithPhone.isPresent()){
            if (dto.getPassword().equals(customerWithPhone.get().getPassword())){
                Customers customer= customerWithPhone.get();
                Optional<CustomerActive> checkAlreadyIn= caDao.findByUserId(customer.getCustomerId());
                if(checkAlreadyIn.isPresent()) {
                    throw new LogInException("Already Login");
                }else{
                    String key= RandomString.make(16);
                    checkHistoryLength(customer.getHistory());
                    customer.getHistory().add(0,new LogInHistory(customer.getMobileNumber(), customer.getPassword(), LocalDateTime.now(), null, null));
                    caDao.save(new CustomerActive(customer.getCustomerId(), customer.getPassword(), "Customer", key, LocalDateTime.now()));
                    return cDao.save(customer);
                }
            }else {
                throw new CustomerException("Incorrect Password");
            }
        }
        else {
            if (!dto.getPassword().equals(customerWithEmail.get().getPassword())){
                throw new CustomerException("Incorrect Password");
            }else {
                Customers customer= customerWithEmail.get();
                Optional<CustomerActive> checkAlreadyIn= caDao.findByUserId(customer.getCustomerId());
                if(checkAlreadyIn.isPresent()) {
                    throw new LogInException("Already Login");
                }else{
                    String key= RandomString.make(16);
                    checkHistoryLength(customer.getHistory());
                    customer.getHistory().add(0,new LogInHistory(customer.getEmail(), customer.getPassword(), LocalDateTime.now(), null, null));
                    caDao.save(new CustomerActive(customer.getCustomerId(), customer.getPassword(), "Customer", key, LocalDateTime.now()));
                    return cDao.save(customer);
                }
            }
        }

    }

    @Override
    @Transactional
    public String logOutCustomer(String key) throws LogInException {
        Optional<CustomerActive> checkAlreadyIn= caDao.findByUuId(key);
        if(checkAlreadyIn.isEmpty()){
            throw new LogInException("♣█☻ Key Error ☻█♣");
        }else {
            Optional<Customers> customer= cDao.findByCustomerId(checkAlreadyIn.get().getUserId());
            LogInHistory logInHistory=customer.get().getHistory().get(0);
            logInHistory.setLogOutTime(LocalDateTime.now());
            cDao.save(customer.get());
            caDao.delete(checkAlreadyIn.get());
            return "Thank you ....";
        }
    }
    public void checkHistoryLength(List<LogInHistory> customerLogHistory){
        if(customerLogHistory.size()==5) {
            //LogInHistory cleaningLast=
            customerLogHistory.remove(4);

        }
    }
    public  CustomerActive checkActiveStatus(String cusID)throws LogInException{
         return caDao.findByUserId(cusID).orElseThrow(() -> new LogInException("No record present"));
    }
//8888888888888888888888888888888888888888888888888888888888 Admin features 88888888888888888888888888888888888888888888888888888888888888888888

    @Override
    public Admin logInAdmin(LogInDTO dto) throws LogInException {
        Optional<Admin> opadmin= aDao.findByEmail(dto.getUsername());

        if (opadmin.isEmpty()){
            throw new LogInException("Incorrect username");
        }
        Optional<CustomerActive> customerActive=caDao.findByUserId(opadmin.get().getAdminId());
        if(customerActive.isPresent()){
            throw new LogInException("User already Logged In with this Email");
        }
        if(opadmin.get().getPassword().equals(dto.getPassword())){
            String key= RandomString.make(16);
            Admin admin= opadmin.get();
            CustomerActive newAdminActive= new CustomerActive(admin.getAdminId(), admin.getPassword(), "Admin", key, LocalDateTime.now());
            caDao.save(newAdminActive);
            return admin;
        }
        else {
            throw new LogInException("Please Enter a valid password");
        }
    }

    @Override
    public String logOutAdmin(String key) throws LogInException {
        Optional<CustomerActive> checkAlreadyIn= caDao.findByUuId(key);
        if(checkAlreadyIn.isEmpty()){
            throw new LogInException("♣█☻ Key Error ☻█♣");
        } else if(checkAlreadyIn.get().getRole().equalsIgnoreCase("admin")) {
            throw new LogInException("♣█☻ Key Error ☻█♣");
        } else {
            caDao.delete(checkAlreadyIn.get());
            return "Thank you ....";
        }
    }
}
