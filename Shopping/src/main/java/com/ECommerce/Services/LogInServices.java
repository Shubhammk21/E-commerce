package com.ECommerce.Services;

import com.ECommerce.DTO.LogInDTO;
import com.ECommerce.Exception.CustomerException;
import com.ECommerce.Exception.LogInException;
import com.ECommerce.Modules.Admin;
import com.ECommerce.Modules.CustomerActive;
import com.ECommerce.Modules.Customers;

public interface LogInServices {
    public Customers logInCustomer(LogInDTO dto) throws LogInException, CustomerException;

    public String logOutCustomer(String key) throws LogInException;

//8888888888888888888888888888888888888888888888888888888 Admin Features 88888888888888888888888888888888888888888888888888888888888888\\

    public Admin logInAdmin(LogInDTO dto) throws LogInException;

    public String logOutAdmin(String key) throws LogInException;

    public CustomerActive checkActiveStatus(String cusID)throws LogInException;

}
