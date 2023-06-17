package com.example.occasion.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.criteria.Order;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

   @NotEmpty(message = "Phone Number is required")
//    @Positive
//    @Column(columnDefinition ="int not null")
    private String phoneNumber;

    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid Email",regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
//    @Column(columnDefinition = "varchar(40) unique")
    private String Email;


    private boolean loyalty = false;

    private Integer numberOfVisit=0;



    @OneToOne
    @MapsId
    @JsonIgnore
    private MyUser myUser;


    @OneToMany(cascade = CascadeType.REMOVE,mappedBy = "customer")
    @JsonIgnore
    private Set<Myorder> myorderSet;

}
