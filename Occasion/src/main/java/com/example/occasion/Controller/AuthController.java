package com.example.occasion.Controller;

import com.example.occasion.Model.MyUser;
import com.example.occasion.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;


    @GetMapping("/get")
    public ResponseEntity getAllUsers(){
        List<MyUser> myUserList=authService.getAllUser();
        return ResponseEntity.status(200).body(myUserList);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody MyUser myUser){
        authService.register(myUser);

        return ResponseEntity.status(200).body("customer register");
    }
//    @PostMapping("/register")
//    public ResponseEntity registercompany(@RequestBody MyUser myUser){
//        authService.registercompany(myUser);
//
//        return ResponseEntity.status(200).body("company register");
//    }

    @PostMapping("/customer")
    public ResponseEntity customer(){

        return ResponseEntity.status(200).body("Welcome customer");
    }
    @PostMapping("/login")
    public ResponseEntity login(){

        return ResponseEntity.status(200).body("login");
    }

    @PostMapping ("/logout")
    public ResponseEntity logout(){

        return ResponseEntity.status(200).body("logout");
    }


    @PostMapping("/company")
    public ResponseEntity company (){

        return ResponseEntity.status(200).body("Welcome Company");
    }
}
