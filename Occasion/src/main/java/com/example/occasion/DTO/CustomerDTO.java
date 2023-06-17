package com.example.occasion.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDTO {

    private String username;
    private String phoneNumber;
    private String email;
    private String password;

}
