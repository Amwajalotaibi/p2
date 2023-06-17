package com.example.occasion.Service;

import com.example.occasion.ApiException.ApiException;
import com.example.occasion.DTO.CompanyDTo;
import com.example.occasion.Model.*;
import com.example.occasion.Repostiroy.AuthRepository;
import com.example.occasion.Repostiroy.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final AuthRepository authRepository;

    public List<Company> getAllCompany() {
        return companyRepository.findAll();
    }

    public void addCompany(CompanyDTo dTo) {
        MyUser myUser=new MyUser(null, dTo.getUsername(), dTo.getPassword(),"company",null,null);
        String hash=new BCryptPasswordEncoder().encode(myUser.getPassword());
        myUser.setPassword(hash);
        authRepository.save(myUser);

        Company company = new Company(null,dTo.getLicensenumber(),dTo.getCity(), dTo.getTimecatgory(), dTo.getRating(), dTo.getPrice(),myUser,null,null);
        companyRepository.save(company);
    }


    public void updateCompany(CompanyDTo dTo,MyUser myUser) {
        MyUser myUser1 = authRepository.findMyUserById(myUser.getId());
        if (myUser1 == null) {
            throw new ApiException("Company not found");
        }

        Company company = companyRepository.findCompanyById(myUser.getId());
        company.setCity(dTo.getCity());
        company.setPrice(dTo.getPrice());
        company.setTimecatgory(dTo.getTimecatgory());
        company.setLicensenumber(dTo.getLicensenumber());
        companyRepository.save(company);
    }

    public void deleteCompany(Integer id){
        Company company=companyRepository.findCompanyById(id);
        if(company==null)
            throw new ApiException("Company not found");
        companyRepository.delete(company);
    }

    public List<Company> getCompanyByCity(String city) {
        List<Company> companys = companyRepository.findCompanyByCity(city);
        if (companys == null) {
            throw new ApiException(" not found");
        }
        return companys;
    }
    public List<Company> getCompanybytimecatgory(String timecatgory) {
        List<Company> companys =companyRepository.findCompanyByTimecatgory(timecatgory);
        if (companys == null) {
            throw new ArithmeticException(" not found ");
        }
        return companys;
    }


    public Company findCompanyByRating(String rating) {
        Company company = companyRepository.findCompanyByRating(rating);
        if (company == null)
            throw new ApiException("Not found");
        companyRepository.save(company);
       return company;
    }



}
