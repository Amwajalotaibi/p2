package com.example.occasion.Service;

import com.example.occasion.ApiException.ApiException;
import com.example.occasion.DTO.CompanyDTo;
import com.example.occasion.DTO.ServicetypeDto;
import com.example.occasion.Model.Company;
import com.example.occasion.Model.MyService;
import com.example.occasion.Model.MyUser;
import com.example.occasion.Model.Servicetype;
import com.example.occasion.Repostiroy.AuthRepository;
import com.example.occasion.Repostiroy.CompanyRepository;
import com.example.occasion.Repostiroy.MyServiceRepository;
import com.example.occasion.Repostiroy.ServicetypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicetypeService {
    private final ServicetypeRepository servicetypeRepository;
    private final MyServiceRepository myServiceRepository;
    private final CompanyRepository companyRepository;

    public List<Servicetype>getAll() {

        return servicetypeRepository.findAll();
    }

    public void addservicetype(ServicetypeDto dto,MyUser myUser) {
        MyService myService = myServiceRepository.findMyServiceById(dto.getMyservice_id());
        Company company=companyRepository.findCompanyById(myUser.getId());
        if (myService == null) {
            throw new ApiException("sorry can't add");
        }
        Servicetype servicetype = new Servicetype(null, dto.getName(), dto.getDetails(), dto.getPrice(),myService);
        myService.getCompanySet().add(company);
        servicetypeRepository.save(servicetype);
    }

    public void updateServicetype(ServicetypeDto dTo) {
        MyService myService = myServiceRepository.findMyServiceById(dTo.getMyservice_id());
        if (myService == null) {
            throw new ApiException("MyUser not found");
        }

        Servicetype servicetype = servicetypeRepository.findServicetypeById(dTo.getMyservice_id());
        servicetype.setName(dTo.getName());
        servicetype.setDetails(dTo.getDetails());
        servicetype.setPrice(dTo.getPrice());
        servicetypeRepository.save(servicetype);
    }

    public void deleteServicetype(Integer id){
        Servicetype servicetype=servicetypeRepository.findServicetypeById(id);
        if(servicetype==null)
            throw new ApiException("Company not found");
        servicetypeRepository.delete(servicetype);
    }


    public List<Servicetype> getServicetypeByName(String name){
        List<Servicetype> servicetypeList=servicetypeRepository.findServicetypeByName(name);
        if(servicetypeList.size() == 0 ){
            throw new ArithmeticException("Service Type not found");
        }
        return servicetypeList;
    }

    public String getDetailsByServicetype(Integer servicetype_id){
        Servicetype servicetype=servicetypeRepository.findServicetypeById(servicetype_id);
        if(servicetype==null){
            throw new ArithmeticException("Service Type not found");
        }
        return servicetype.getDetails();
    }

    public Integer getPriceByServicetype(Integer servicetype_id){
        Servicetype servicetype=servicetypeRepository.findServicetypeById(servicetype_id);
        if (servicetype==null) {
            throw new ApiException("id wrong,Service Type not found");
        }
        return servicetype.getPrice();
    }

    public void discount(Integer amount){
        List<Servicetype> servicetypeList=servicetypeRepository.findAll();
        for ( Servicetype s :servicetypeList){
            s.setPrice(s.getPrice()-(s.getPrice()*amount/100));
            servicetypeRepository.save(s);
        }
    }

}

