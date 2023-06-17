package com.example.occasion.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @NotEmpty(message = "Role can't be empty")
//   @Column(columnDefinition = "varchar(20) not null check(role ='licensenumber')")
    private String licensenumber;
//
//    @NotEmpty(message = "city can't be empty")
//    @Column(columnDefinition = "varchar(20) not null")
    private String city;

//    @NotEmpty(message = "time catgory can't be empty")
//    @Column(columnDefinition = "varchar(20) not null check(timecatgory ='available' or timecatgory='busy')")
    private String timecatgory;

//    @NotEmpty(message = "rating can't be empty")
//    @Column(columnDefinition = "varchar(20) not null")
    private String rating;

//    @NotNull(message = "price can't be Null")
//    @Min(3)
//    @Max(50)
//    @Column(columnDefinition = "int not null")
    private Integer Price;



    @OneToOne
    @MapsId
    @JsonIgnore
    private MyUser myUser;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    @JsonIgnore
    private Set<Myorder> myorderSet;


    @ManyToMany
    @JsonIgnore
    @JoinColumn(name = "company_id")
    private Set<MyService> myServiceSet;
 }



