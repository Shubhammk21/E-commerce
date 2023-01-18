package com.ECommerce.Modules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "demo_sql")
    @GenericGenerator(name = "demo_sql",strategy = "com.ECommerce.Modules.StringSequenceGenerator",parameters = {
            @Parameter(name = StringSequenceGenerator.INCREMENT_PARAM,value="1"),
            @Parameter(name = StringSequenceGenerator.VALUE_PREFIX_PARAMETER, value = "Seller_"),
            @Parameter(name = StringSequenceGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")

    })
    private String sellerID;

    @NotNull(message = "first name cannot be null")
    private String CompanyName;

    @NotNull(message = "mobile number cannot be null")
    @Pattern(regexp = "[7,8,9]{1}[0-9]{10}",message = "Invalid mobile number")
    private String mobileNumber;
    @Email
    private String email;

    @NotNull(message = "Password cannot be null")
    @Size(min = 8,max = 15,message = "Password should be min 8 and max 15 character length.")
    private String password;

    @NotNull(message = "city cannot can not be null")
    private String city;

    @NotNull(message = "state can not be null")
    private String state;

    @NotNull(message = "country can not be null")
    private String country;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "seller")
    private List<Products> products;

}
