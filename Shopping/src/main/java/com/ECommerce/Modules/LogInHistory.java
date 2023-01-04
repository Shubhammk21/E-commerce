package com.ECommerce.Modules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.stream.Location;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogInHistory {
    @Id
    private String userId;
    private String password;
    private LocalDateTime logInTime;
    private LocalDateTime LogOutTime;
    private String location;


}
