package com.ECommerce.Modules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import javax.persistence.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Admin {
    @Id
    @NotNull(message = "userid cannot be null")
    private String adminId;

    @NotNull(message = "password cannot be null")
    private String password;

    @NotNull(message = "first name cannot be null")
    private String firstName;

    @NotNull(message = "last name cannot be null")
    private String lastName;
    @NotNull(message = "mobile number cannot be null")
    @Pattern(regexp = "[7,8,9]{1}[0-9]{9}",message = "Invalid mobile number")
    private String mobileNumber;

}
