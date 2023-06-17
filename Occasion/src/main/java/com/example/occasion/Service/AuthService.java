package com.example.occasion.Service;


import com.example.occasion.Model.MyUser;
import com.example.occasion.Repostiroy.AuthRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;


    public List<MyUser> getAllUser(){

        return authRepository.findAll();
    }

}
