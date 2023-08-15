package com.ECommerce.Modules;

import com.ECommerce.DTO.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int categoryID;

    @NotNull(message = "Category type not null")
    private Gender categoryType;
    @NotNull
    @NotBlank
    private String categoryName;

    @NotNull
    @NotBlank
    private String subCategory;

    private String active;

//    @OneToMany(cascade = CascadeType.ALL,mappedBy = "category")
//    @JsonIgnore
//    private List<Products> productsList;


}
