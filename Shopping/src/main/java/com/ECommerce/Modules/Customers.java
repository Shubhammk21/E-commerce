package com.ECommerce.Modules;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "demo_sql")
    @GenericGenerator(name = "demo_sql",strategy = "com.ECommerce.Modules.StringSequenceGenerator",parameters = {
            @Parameter(name = StringSequenceGenerator.INCREMENT_PARAM,value="1"),
            @Parameter(name = StringSequenceGenerator.VALUE_PREFIX_PARAMETER, value = "Customer_"),
            @Parameter(name = StringSequenceGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")

    })
    private String customerId;

    @NotNull(message = "first name cannot be null")
    private String firstName;

    @NotNull(message = "last name cannot be null")
    private String lastName;

    //@NotNull(message = "mobile number cannot be null")
    //@Pattern(regexp = "[7,8,9]{1}[0-9]{9}",message = "Invalid mobile number")
    private String mobileNumber;
    private String email;
    @NotNull(message = "Password cannot be null")
    @Size(min = 8,max = 15,message = "Password should be min 8 and max 15 character length.")
    private String password;

    @NotNull(message = "dob cannot be null")
    private String dob;
    @NotNull(message = "Gender cannot be null")
    private String gender;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "customerId")
    @JsonIgnore
    private List<LogInHistory> history= new ArrayList<>(5);

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId")
    @JsonIgnore
    private CustomerActive customerActive;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
    @JsonIgnore
    private List<Address> address ;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "customer")
    @JsonIgnore
    private Cart cart;




    public Customers(String firstName, String lastName, String mobileNumber, String email, String password, String dob, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
    }
}
