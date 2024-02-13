package com.example.jdbc.app;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Customer {
    private long id;
    private String fio;
    private String phone;
    private String adress;
    private LocalDateTime  created;

}
