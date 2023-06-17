package com.example.occasion.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServicetypeDto {
    private Integer myservice_id;
    private String name;
    private String details;
    private Integer price;


}

