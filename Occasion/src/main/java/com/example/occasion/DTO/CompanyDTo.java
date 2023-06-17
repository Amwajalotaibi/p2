package com.example.occasion.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyDTo {
    private Integer company_Id;

    private String username;
    private String password;
    private String city;
    private Integer Price;
    private String timecatgory;
    private String rating;
    private String licensenumber;


}
