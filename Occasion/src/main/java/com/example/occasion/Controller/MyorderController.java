package com.example.occasion.Controller;

import com.example.occasion.Model.Company;
import com.example.occasion.Model.Customer;
//import com.example.occasion.Model.MyUser;
import com.example.occasion.Model.MyUser;
import com.example.occasion.Model.Myorder;
import com.example.occasion.Service.MyorderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/myorder")
@RequiredArgsConstructor
public class MyorderController {
    private final MyorderService myorderService;

    @GetMapping("/get")
    public ResponseEntity getAll() {

        return ResponseEntity.status(200).body(myorderService.getAll());
    }


    @PostMapping("/add/{serviceId}/{companyId}")
    public ResponseEntity addMyorder(@Valid @RequestBody Myorder myorder,@PathVariable Integer serviceId,@PathVariable Integer companyId,@AuthenticationPrincipal MyUser myUser) {
        myorderService.addMyorder(myorder,myUser,serviceId,companyId);
        return ResponseEntity.status(200).body("My Order Added");
    }

    @PutMapping("/update/{myorderId}")
    public ResponseEntity updateTodo(@RequestBody Myorder myorder, @PathVariable Integer myorderId) {
        myorderService.updateMyorder(myorder, myorderId);
        return ResponseEntity.status(200).body("My Order Update ");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMyorder(@PathVariable Integer id) {
        myorderService.deleteMyorder(id);
        return ResponseEntity.status(200).body("My Order deleted");

    }

    @GetMapping("/get-id/{id}")
    public ResponseEntity getMyOrderById(@PathVariable Integer id) {
        myorderService.findMyorderById(id);
        return ResponseEntity.status(200).body("Get by Id");
    }

    @GetMapping("/get-day/{day}")
    public ResponseEntity getMyOrderByDay(@PathVariable Integer day) {
        return ResponseEntity.status(200).body("order day is :" + myorderService.getOrderByDay(day));
    }

    @GetMapping("/get-status/{status}")
    public ResponseEntity getMyorderStatus(@PathVariable String status) {
            return ResponseEntity.status(200).body("My order status is:"+ myorderService.getStutasofMyorder(status));
        }

    @GetMapping("subscription/{category}")
    public ResponseEntity subscription (@Valid @PathVariable String category,@PathVariable Integer numbarofrepeat,@PathVariable Integer customerId,@PathVariable Integer myorderId) {
        myorderService.subscription(numbarofrepeat,category,myorderId,customerId);
        return ResponseEntity.status(200).body("subscription");
    }



}
