package com.ECommerce.Modules;

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
    @NotNull
    @NotBlank
    private String categoryName;

    @NotNull
    @NotBlank
    private String active;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "category")
    //@JoinColumn(name = "categoryID")
    private List<Products> productsList;

}
