package com.example.occasion.Service;

import com.example.occasion.Model.Company;
import com.example.occasion.Model.MyUser;
import com.example.occasion.Repostiroy.AuthRepository;
import com.example.occasion.Repostiroy.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
//    private final CompanyRepository companyRepository;

    public List<MyUser> getAllUser(){

        return authRepository.findAll();
    }

    public void register(MyUser myUser){
        String hash=new BCryptPasswordEncoder().encode(myUser.getPassword());
        myUser.setPassword(hash);
        myUser.setRole("CUSTOMER");
        authRepository.save(myUser);
    }




//    public void registercompany(MyUser myUser){
//        String hash=new BCryptPasswordEncoder().encode(myUser.getPassword());
//        myUser.setPassword(hash);
//        myUser.setRole("COMPANY");
//
//
//        authRepository.save(myUser);
//    }
}
