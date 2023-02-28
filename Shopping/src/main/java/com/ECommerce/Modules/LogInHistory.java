package com.ECommerce.Modules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.stream.Location;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogInHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int HID;
    private String userId;
    private String password;
    private LocalDateTime logInTime;
    private LocalDateTime LogOutTime;
    private String location;

    public LogInHistory(String userId, String password, LocalDateTime logInTime, LocalDateTime logOutTime, String location) {
        this.userId = userId;
        this.password = password;
        this.logInTime = logInTime;
        LogOutTime = logOutTime;
        this.location = location;
    }
}
