package com.example.occasion.Service;

import com.example.occasion.ApiException.ApiException;
import com.example.occasion.DTO.CompanyDTo;
import com.example.occasion.DTO.CustomerDTO;
import com.example.occasion.Model.Company;
import com.example.occasion.Model.Customer;
import com.example.occasion.Model.MyUser;
import com.example.occasion.Model.Myorder;
import com.example.occasion.Repostiroy.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final AuthRepository authRepository;
    private final MyorderRepository myorderRepository;


    public List<Customer> getAll() {

        return customerRepository.findAll();
    }

    public void addCustomer(CustomerDTO dto) {
        MyUser myUser=new MyUser(null, dto.getUsername(), dto.getPassword(),"customer",null,null);
//        String hash=new BCryptPasswordEncoder().encode(myUser.getPassword());
//        myUser.setPassword(hash);
        authRepository.save(myUser);


        Customer customer = new Customer(null, dto.getEmail(), dto.getPhoneNumber(), false, 0,null,null);
        customerRepository.save(customer);
    }


//    public void updateCustomer(CustomerDTO dto) {
//        MyUser myUser = authRepository.findMyUserById(dto.);
//        if (myUser == null) {
//            throw new ApiException("MyUser not found");
//        }
//
//        Customer customer = customerRepository.getCustomerById(dto.getCustomer_id());
//        customer.setEmail(dto.getEmail());
//        customer.setPhoneNumber(dto.getPhoneNumber());
//        customerRepository.save(customer);
//    }

    public void deleteCustomer(Integer id) {
        Customer c = customerRepository.getCustomerById(id);
        if (c == null)
            throw new ApiException("Not found");
        customerRepository.delete(c);
    }


    public Set<Myorder> getMyOrderofCustomer(Integer id) {
        Customer customer = customerRepository.getCustomerById(id);
        if (customer == null) {
            throw new ArithmeticException("Not found");
        }
            return customer.getMyorderSet();

    }

        public void loyalty (Integer customerId, Integer myOrderId){
            Customer customer = customerRepository.getCustomerById(customerId);
            Myorder myorder = myorderRepository.findMyorderById(myOrderId);
            if (customer == null || myorder == null || myorder.getCustomer() != customer)
                throw new ApiException("Not found");
            Integer price = myorder.getTotalPrice();
            if (customer.getNumberOfVisit() > 2) {
                customer.setLoyalty(true);
                myorder.setTotalPrice(price - (price * 20 / 100));
                customerRepository.save(customer);
                myorderRepository.save(myorder);
            } else
                throw new ApiException("Not enough visits");
        }

    }

