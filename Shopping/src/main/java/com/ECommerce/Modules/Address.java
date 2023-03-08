package com.ECommerce.Modules;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ECommerce.DTO.AddressType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "demo_sql")
    @GenericGenerator(name = "demo_sql",strategy = "com.ECommerce.Modules.StringSequenceGenerator",parameters = {
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

    @ManyToOne
    private Customers customer;

}
