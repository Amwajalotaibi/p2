package com.example.occasion.Repostiroy;

import com.example.occasion.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<MyUser,Integer> {
    MyUser findMyUserByUsername(String username);
    MyUser findMyUserById(Integer id);
}
