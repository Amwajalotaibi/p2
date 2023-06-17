package com.example.occasion.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Myorder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "day not null")
    @Column(columnDefinition = "int not null")
    private Integer day;

    @NotEmpty(message = "date can't be empty")
    @Column(columnDefinition = "varchar(10) not null")
    private String date;

    @NotEmpty(message = "Status can't be empty")
    @Column(columnDefinition = "varchar(20) not null" )
    private String time;

    @NotNull(message = "total price not null")
    @Column(columnDefinition = "int not null")
    private Integer totalPrice;

    private String stutas;

    private LocalDateTime createAt;






    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Customer customer;


    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonIgnore
    private Company company;


    @ManyToOne
    @JoinColumn(name = "myService_id")
    @JsonIgnore
    private MyService myService;


    @OneToOne(cascade =CascadeType.ALL,mappedBy = "myOrder")
    @PrimaryKeyJoinColumn
    private Rating rating;


}
