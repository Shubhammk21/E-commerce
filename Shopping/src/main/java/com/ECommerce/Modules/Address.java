package com.ECommerce.Modules;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    @NotNull(message = "streetNo can not be null")
    private String streetNo;

    @NotNull(message = "building name can not be null")
    private String buildingName;

    @NotNull(message = "city cannot can not be null")
    private String city;

    @NotNull(message = "state can not be null")
    private String state;

    @NotNull(message = "country can not be null")
    private String country;

    @NotNull(message = "pinCode can not be null")
    @Size(min = 5, max = 8, message = "pincode size cannot be greater then 6")
    private String pinCode;

    @ManyToOne
    private Customers customer;

}
