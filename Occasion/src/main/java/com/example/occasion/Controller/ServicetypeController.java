package com.example.occasion.Controller;

import com.example.occasion.DTO.ServicetypeDto;
import com.example.occasion.Model.MyUser;
import com.example.occasion.Service.ServicetypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Repository
@RequestMapping("/api/v1/servicetype")
public class ServicetypeController {
    private final ServicetypeService servicetypeService;

    @GetMapping("/get")
    public ResponseEntity getAll(){

        return ResponseEntity.status(200).body(servicetypeService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity addCustomerDto(@Valid @RequestBody ServicetypeDto servicetypeDto, @AuthenticationPrincipal MyUser myUser){
        servicetypeService.addservicetype(servicetypeDto,myUser);
        return ResponseEntity.status(200).body("Company added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateservicedto(@Valid @RequestBody ServicetypeDto servicetypeDto){
        servicetypeService.updateServicetype(servicetypeDto);
        return ResponseEntity.status(200).body("Service Type Updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteaServicetype(@PathVariable Integer id){
        servicetypeService.deleteServicetype(id);
        return ResponseEntity.status(200).body("Service Type deleted");
    }

    @GetMapping("/get-name/{name}")
    public ResponseEntity getServicetypeByName(@PathVariable String name){
        servicetypeService.getServicetypeByName(name);
        return ResponseEntity.status(200).body("get by name");
    }

    @GetMapping("/get-price/{servicetype_id}")
    public ResponseEntity getPriceByServicetype(@PathVariable Integer servicetype_id){
        return ResponseEntity.status(200).body("The price is = "+servicetypeService.getPriceByServicetype(servicetype_id));
    }
    @GetMapping("/get-details/{servicetype_id}")
    public ResponseEntity getDetailsByServicetype(@PathVariable Integer servicetype_id){
        return ResponseEntity.status(200).body("The Details is = "+servicetypeService.getDetailsByServicetype(servicetype_id));
    }

    @PutMapping("/discount/{amount}")
    public ResponseEntity discount(@PathVariable  Integer amount){
        servicetypeService.discount(amount);
        return ResponseEntity.status(200).body("discount successful");
    }

}

