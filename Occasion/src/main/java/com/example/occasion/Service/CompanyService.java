package com.example.occasion.Service;

import com.example.occasion.ApiException.ApiException;
import com.example.occasion.DTO.CompanyDTo;
import com.example.occasion.Model.*;
import com.example.occasion.Repostiroy.AuthRepository;
import com.example.occasion.Repostiroy.CompanyRepository;
import com.example.occasion.Repostiroy.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
//    public List<Company> getCompany( Integer userId) {
//        MyUser myUser=authRepository.findMyUserById(userId);
//        return companyRepository.findAllByMyUser(myUser);
//    }

    public void addCompany(CompanyDTo dTo) {
        MyUser myUser=new MyUser(null, dTo.getUsername(), dTo.getPassword(),"company",null,null);
//        String hash=new BCryptPasswordEncoder().encode(myUser.getPassword());
//        myUser.setPassword(hash);
        authRepository.save(myUser);

        Company company = new Company(null,dTo.getLicensenumber(),dTo.getCity(), dTo.getTimecatgory(), dTo.getRating(), dTo.getPrice(),myUser,null,null);
        companyRepository.save(company);
    }

//    public void updateCompany(CompanyDTo dTo) {
//        MyUser myUser = authRepository.findMyUserById(dTo.getId());
//        if (myUser == null) {
//            throw new ApiException("Company not found");
//        }
//
//        Company company = companyRepository.findCompanyById(myUser.getId());
//        company.setCity(dTo.getCity());
//        company.setPrice(dTo.getPrice());
//        company.setTimecatgory(dTo.getTimecatgory());
//       company.setLicensenumber(dTo.getLicensenumber());
//        companyRepository.save(company);
//    }

    public void deleteCompany(Integer id){
        Company company=companyRepository.findCompanyById(id);
        if(company==null)
            throw new ApiException("Company not found");
        companyRepository.delete(company);
    }

    public Company getCompanyByCity(String city) {
        Company company = companyRepository.findCompanyByCity(city);
        if (company == null) {
            throw new ApiException(" not found");
        }
        return company;
    }
    public Company getCompanybytimecatgory(String timecatgory) {
        Company company1 =companyRepository.findCompanyByTimecatgory(timecatgory);
        if (company1 == null) {
            throw new ArithmeticException(" not found ");
        }
        return company1;
    }


    public Company findCompanyByRating(String rating) {
        Company company = companyRepository.findCompanyByRating(rating);
        if (company == null)
            throw new ApiException("Not found");
        companyRepository.save(company);
       return company;
    }

//    public void updateCompany(Integer id , Company newCompany , Integer auth){
//        Company oldCompany=companyRepository.findCompanyById(id);
//        MyUser myUser=authRepository.findMyUserById(auth);
//
//        if (oldCompany==null){
//            throw new ApiException("Todo not found");
//        }else if(oldCompany.getMyUser().getId()!=auth){
//            throw new ApiException("Sorry , You do not have the authority to update this Company!");
//        }
//
//        newCompany.setId(id);
//        newCompany.setMyUser(myUser);
//
//        companyRepository.save(newCompany);
//    }


}
