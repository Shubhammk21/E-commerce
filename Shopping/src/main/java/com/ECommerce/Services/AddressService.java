package com.ECommerce.Services;

import com.ECommerce.Exception.AddressException;
import com.ECommerce.Modules.Address;

import java.util.Set;

public interface AddressService {

    public Address addAddress(String customerId, Address address) throws AddressException;
    public Address updateAddress(Address address) throws AddressException;
    public Address deleteAddress(String addressId) throws AddressException;

    public Set<Address> getAddresses(String customerId) throws AddressException;
}
