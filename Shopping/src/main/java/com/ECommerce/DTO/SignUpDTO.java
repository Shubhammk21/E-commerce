package com.ECommerce.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDTO {

    @NotNull(message = "first name cannot be null")
    private String firstName;
    @NotNull(message = "last name cannot be null")
    private String lastName;
    @NotNull(message = "userid cannot be null")
    private String username;
    @NotNull(message = "password cannot be null")
    private String password;
    @NotNull(message = "dob cannot be null")
    private String dob;
    @NotNull(message = "Gender cannot be null")
    private String gender;

}
