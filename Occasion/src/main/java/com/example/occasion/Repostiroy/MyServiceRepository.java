package com.example.occasion.Repostiroy;

import com.example.occasion.Model.MyService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyServiceRepository extends JpaRepository<MyService,Integer> {
    MyService findMyServiceById(Integer id);

    List<MyService> findMyServiceByCategory(String category);
}
