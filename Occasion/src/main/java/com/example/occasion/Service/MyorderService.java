package com.example.occasion.Service;

import com.example.occasion.ApiException.ApiException;
import com.example.occasion.Model.*;
import com.example.occasion.Repostiroy.CompanyRepository;
import com.example.occasion.Repostiroy.CustomerRepository;
import com.example.occasion.Repostiroy.MyServiceRepository;
import com.example.occasion.Repostiroy.MyorderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MyorderService {

    private final MyorderRepository myorderRepository;
    private  final MyServiceRepository myServiceRepository;
    private final CustomerRepository customerRepository;
    private final CompanyRepository companyRepository;


    public List<Myorder> getAll() {

        return myorderRepository.findAll();
    }


    public void addMyorder(Myorder myorder, MyUser myUser,Integer serviceId, Integer companyId) {
        MyService myService=myServiceRepository.findMyServiceById(serviceId);
        Company company=companyRepository.findCompanyById(companyId);
        if (myService==null || company==null)
            throw new ApiException("Not found");
        myorder.setCustomer(myUser.getCustomer());
        myorder.setStutas("new");
        myorder.setMyService(myService);
        myorder.setTotalPrice(myService.getServicetype().getPrice());
        myorder.setCompany(company);

        myorderRepository.save(myorder);
    }

    public void updateMyorder(Myorder myorder, Integer id) {
        Myorder oldMyorder = myorderRepository.findMyorderById(id);

        if (oldMyorder == null) {
            throw new ApiException("Order not found");
        }
        oldMyorder.setDay(myorder.getDay());
        oldMyorder.setDate(myorder.getDate());
        oldMyorder.setTime(myorder.getTime());
        myorderRepository.save(oldMyorder);
    }

    public void deleteMyorder(Integer id) {
        Myorder myorder = myorderRepository.findMyorderById(id);
        if (myorder == null) {
            throw new ApiException("Order not found");
        }

        myorderRepository.delete(myorder);
    }

    public Myorder findMyorderById(Integer id) {
        Myorder myorder = myorderRepository.findMyorderById(id);
        if (myorder == null) {
            throw new ArithmeticException(" Wrong ID ");
        }
        return myorder;
    }

    public List<Myorder> getOrderByDay(Integer day) {
        return myorderRepository.findMyorderByDay(day);

    }

    public List<Myorder> getStutasofMyorder(String stutas ) {
        return myorderRepository.findMyorderByStutas(stutas);
    }

///////////////////////////////////
    public void subscription (Integer numbarofrepeat,String category,Integer myorderId,Integer customerId){
        Myorder myorder=myorderRepository.findMyorderById(myorderId);
        Customer customer=customerRepository.getCustomerById(customerId);
        if(customer!=myorder.getCustomer()){
            throw new ApiException("Not customer");
        }
        myorder.setNumbarofrepeat(numbarofrepeat);
        myorder.setCategory(category);
        myorderRepository.save(myorder);

    }
////////////////////////////////////


}