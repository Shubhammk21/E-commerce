package com.ECommerce.Services;

import com.ECommerce.Exception.AddressException;
import com.ECommerce.Modules.Address;
import com.ECommerce.Modules.Customers;
import com.ECommerce.Repository.AddressRepo;
import com.ECommerce.Repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class AddressServiceImply implements AddressService{

    @Autowired
    private AddressRepo aRepo;

    @Autowired
    private CustomerRepo cRepo;

    @Override
    public Address addAddress(String customerId, Address address) throws AddressException {
        Optional<Customers> optionalCustomers= cRepo.findByCustomerId(customerId);
        if(optionalCustomers.isEmpty()){
            throw new AddressException("Something wrong please try after some time");
        }else{
            Set<Address> uniqueAddress= optionalCustomers.get().getAddress();
            uniqueAddress.add(address);
            optionalCustomers.get().setAddress(uniqueAddress);
            cRepo.save(optionalCustomers.get());
            return address;
        }
    }

    @Override
    public Address updateAddress(Address address) throws AddressException {
        Address opAddress= aRepo.findById(address.getAddressId()).orElseThrow(()-> new AddressException("Someting wrong on address"));
        return aRepo.save(address);
    }

    @Override
    public Address deleteAddress(String addressId) throws AddressException {
        Address address= aRepo.findById(addressId).orElseThrow(()-> new AddressException("Address_ID is wrong"));
        aRepo.delete(address);
        return address;
    }

    @Override
    public Set<Address> getAddresses(String customerId) throws AddressException {
        Customers customers= cRepo.findById(customerId).orElseThrow(()-> new AddressException("Something wrong"));
        return customers.getAddress();
    }
}
