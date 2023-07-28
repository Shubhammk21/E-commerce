package com.ECommerce.Modules;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ECommerce.DTO.AddressType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "demoAddress_sql")
    @GenericGenerator(name = "demoAddress_sql",strategy = "com.ECommerce.Modules.StringSequenceGenerator",parameters = {
            @Parameter(name = StringSequenceGenerator.INCREMENT_PARAM,value="1"),
            @Parameter(name = StringSequenceGenerator.VALUE_PREFIX_PARAMETER, value = "AID_"),
            @Parameter(name = StringSequenceGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")

    })
    private String addressId;

    @NotNull
    private String aName;

    private String APhoneNum;

    @NotNull(message = "pinCode can not be null")
    @Size(min = 5, max = 8, message = "PinCode size cannot be greater then 6")
    private String pinCode;

    @NotNull(message = "locality can not be null")
    private String locality;

    @NotNull(message = "Fill streetAddress")
    private String streetAddress;

    @NotNull(message = "city cannot can not be null")
    private String city;

    @NotNull(message = "state can not be null")
    private String state;

    @NotNull(message = "country can not be null")
    private String country;

    private String landmark;
    private String APhoneNumAlternative;

    @NotNull(message = "Address type not null")
    private AddressType addressType;

//    @ManyToOne
//    @JsonIgnore()
//    private Customers customer;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address address)) return false;
        return aName.equals(address.aName) && getAPhoneNum().equals(address.getAPhoneNum()) && getPinCode().equals(address.getPinCode()) && getLocality().equals(address.getLocality()) && getStreetAddress().equals(address.getStreetAddress()) && getCity().equals(address.getCity()) && getState().equals(address.getState()) && getCountry().equals(address.getCountry()) && Objects.equals(getLandmark(), address.getLandmark()) && Objects.equals(getAPhoneNumAlternative(), address.getAPhoneNumAlternative()) && getAddressType() == address.getAddressType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(aName, getAPhoneNum(), getPinCode(), getLocality(), getStreetAddress(), getCity(), getState(), getCountry(), getLandmark(), getAPhoneNumAlternative(), getAddressType());
    }
}
