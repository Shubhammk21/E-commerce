package com.ECommerce.Modules;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.awt.*;
import java.sql.Blob;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "Product_sql")
    @GenericGenerator(name = "demo_sql",strategy = "com.ECommerce.Modules.StringSequenceGenerator",parameters = {
            @Parameter(name = StringSequenceGenerator.INCREMENT_PARAM,value="1"),
            @Parameter(name = StringSequenceGenerator.VALUE_PREFIX_PARAMETER, value = "PID_"),
            @Parameter(name = StringSequenceGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")

    })
    private int productId;

    //@OneToMany(cascade = CascadeType.ALL)
    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Images> images=new HashSet<>();
    @NotNull(message = "Enter Product Name")
    private String productName;

    @NotNull(message = "Enter Product Price")
    private double marketPrice;
    @NotNull(message = "Enter Product Price")
    private double sellPrice;

    @NotNull(message = "Enter Product Dimension")
    private String dimension;

    @NotNull(message = "Enter Product Specification")
    private String specification;

    @NotNull(message = "Enter Product Manufacturer")
    private String manufacturer;

    @NotNull(message = "Enter Product Quantity")
    private Integer quantity;

    @NotNull(message = "Enter Product Price")
    private String Type;

    @ManyToOne
    @JsonIgnore
    private Category category;

    @ManyToOne
    private Seller seller;


}
