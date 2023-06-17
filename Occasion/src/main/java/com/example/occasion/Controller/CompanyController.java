package com.example.occasion.Controller;

import com.example.occasion.DTO.CompanyDTo;
import com.example.occasion.Model.Company;
import com.example.occasion.Model.MyUser;
import com.example.occasion.Service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("/get")
    public ResponseEntity<List<Company>> getAllCompanys(@AuthenticationPrincipal MyUser myUser){
        return ResponseEntity.status(200).body(companyService.getAllCompany());
    }

//    @GetMapping("/get")
//    public ResponseEntity getCompany(@AuthenticationPrincipal MyUser myUser){
//        List<Company> companyList=companyService.getCompany(myUser.getId());
//        return ResponseEntity.status(200).body(companyList);
//    }

    @PostMapping("/add")
    public ResponseEntity addCompanyDto(@AuthenticationPrincipal MyUser myUser,@Valid @RequestBody CompanyDTo companyDTo){
        companyService.addCompany(companyDTo);
        return ResponseEntity.status(200).body("Company added");
    }

    //    @PutMapping("/update/{id}")
//    public ResponseEntity updateCompany(@AuthenticationPrincipal MyUser myUser,@Valid @RequestBody CompanyDTo companyDTo){
//        companyService.updateCompany(companyDTo);
//        return ResponseEntity.status(200).body("Company Updated");
//    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCompany(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer id){
        companyService.deleteCompany(id);
        return ResponseEntity.status(200).body("Company deleted");

    }

    @GetMapping("/get-company/{city}")
    public ResponseEntity getCompanybyCity(@AuthenticationPrincipal MyUser myUser,@RequestBody CompanyDTo companyDTo ) {
        companyService.getCompanyByCity(companyDTo.getCity());
        return ResponseEntity.status(200).body("company by city");
    }

    @GetMapping("/get-company/{timecatgory}")
    public ResponseEntity getcompanyBytimecatgory(@AuthenticationPrincipal CompanyDTo companyDTo  ) {
        return ResponseEntity.status(200).body(" The Company is:"+companyService.getCompanybytimecatgory(companyDTo.getTimecatgory()));
    }
    @GetMapping("/get-company/{rating}")
    public ResponseEntity getCompanyByRating(@AuthenticationPrincipal  CompanyDTo companyDTo ) {
        companyService.findCompanyByRating(companyDTo.getRating());
        return ResponseEntity.status(200).body("company by rating");
    }



//    @PutMapping ("/update/{companyId}")
//    public ResponseEntity updateCompany(@AuthenticationPrincipal MyUser myUser,@RequestBody Company company,@PathVariable Integer companyId){
//        companyService.updateCompany(myUser.getId(),company,companyId);
//        return ResponseEntity.status(200).body("Company Update ");
//    }
//   @DeleteMapping("/delete/{companyId}")
//    public ResponseEntity deleteCompany(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer companyId,@PathVariable Integer userId){
//    companyService.deleteCompany(myUser.getId(),companyId);
//    return ResponseEntity.status(200).body(" Company delete");
//}
//

}

