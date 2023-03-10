package com.ECommerce.Controllers;

import com.ECommerce.Exception.AddressException;
import com.ECommerce.Modules.Address;
import com.ECommerce.Services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
public class AddressController {
    @Autowired
    private AddressService as;
    @PostMapping("/Add/Address/{customerId}")
    public ResponseEntity<Address> addControllerAddress(@Valid @RequestBody Address address,@PathVariable String customerId) throws AddressException{
        Address address1= as.addAddress(customerId,address);
        return new ResponseEntity<Address>(address1, HttpStatus.CREATED);
    }
    @PutMapping("/UpdateAddress")
    public ResponseEntity<Address> updateAddress(@RequestBody Address address) throws AddressException{
        return new ResponseEntity<Address>(as.updateAddress(address),HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/Delete/{AID}")
    public ResponseEntity<Address> deleteAddress(@PathVariable("AID") String addressId) throws AddressException{
        return new ResponseEntity<Address>(as.deleteAddress(addressId),HttpStatus.OK);
    }

    @GetMapping("/GetAddressList/{customerId}")
    public ResponseEntity<Set<Address>> getAddresses(@PathVariable String customerId) throws AddressException{
        return new ResponseEntity<Set<Address>>(as.getAddresses(customerId),HttpStatus.OK);
    }

}
