package com.example.occasion.Repostiroy;

import com.example.occasion.Model.Customer;
import com.example.occasion.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Customer getCustomerById(Integer id);
    List<Customer> findAllByMyUser(MyUser myUser);


}
