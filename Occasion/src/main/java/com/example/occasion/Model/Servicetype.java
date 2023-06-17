package com.example.occasion.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Servicetype {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Pattern(regexp = "\\b(?:staff|eat|coordination)\\b",message = "Category Not Valid")
    @Column(columnDefinition = "varchar(20) not null check (name='staff' or name='eat' or name='coordination')")
    private String name;

    @NotEmpty
    @Column(columnDefinition = "varchar(20)not null")
    private String details;

    @NotNull(message = "price can't be Null")
    @Min(3)
    @Max(1000)
    @Column(columnDefinition = "int not null")
    private Integer price;




    @OneToOne
    @MapsId
    @JsonIgnore
    private MyService myService;
}
