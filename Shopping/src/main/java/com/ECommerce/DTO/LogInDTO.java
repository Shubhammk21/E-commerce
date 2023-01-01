package com.ECommerce.DTO;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class LogInDTO {

    @NotNull(message = "userid cannot be null")
    private String username;

    @NotNull(message = "password cannot be null")
    private String password;

}
