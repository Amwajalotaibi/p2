package com.example.occasion.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class MyService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @Pattern(regexp = "\\b(?:bigparty|mediumparty|smallparty)\\b",message = "Category Not Valid")
    @Column(columnDefinition = "varchar(20) not null check (category='bigparty' or category='mediumparty' or category='smallparty')")
    private String category;





    @ManyToOne
    @JoinColumn(name = "myOrder_id")
    @JsonIgnore
    private Myorder myorder;


    @ManyToMany (mappedBy = "myServiceSet")
    @JsonIgnore
    private Set<Company> companySet;


    @OneToOne(cascade =CascadeType.ALL,mappedBy = "myService")
    @PrimaryKeyJoinColumn
    private Servicetype servicetype;
}
