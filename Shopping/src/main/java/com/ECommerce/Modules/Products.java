package com.ECommerce.Modules;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "demoProduct_sql")
    @GenericGenerator(name = "demoProduct_sql",strategy = "com.ECommerce.Modules.StringSequenceGenerator",parameters = {
            @Parameter(name = StringSequenceGenerator.INCREMENT_PARAM,value="1"),
            @Parameter(name = StringSequenceGenerator.VALUE_PREFIX_PARAMETER, value = "PID_"),
            @Parameter(name = StringSequenceGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")

    })
    private String productId;

    //@OneToMany(cascade = CascadeType.ALL)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="productId")
    private Set<Images> images=new HashSet<>();
    @NotNull(message = "Enter Product Name")
    private String productName;

    private LocalDateTime addDateTime;
    @NotNull(message = "Enter Product Price")
    private double marketPrice;
    @NotNull(message = "Enter Product Price")
    private double sellPrice;

    @NotNull(message = "Enter Product Dimension")
    private String dimension;

    @NotNull(message = "Enter Product Specification")
    @Lob
    private String specification;

    @NotNull(message = "Enter Product Manufacturer")
    private String brand;

    @NotNull(message = "Enter Product Quantity")
    private int quantity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "categoryId")
    private Category category;

//    @ManyToOne
//    @JsonIgnore
//    private Seller seller;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Products products)) return false;
        return Double.compare(products.getMarketPrice(), getMarketPrice()) == 0 && Double.compare(products.getSellPrice(), getSellPrice()) == 0 && Objects.equals(getImages(), products.getImages()) && Objects.equals(getProductName(), products.getProductName()) && Objects.equals(getDimension(), products.getDimension()) && Objects.equals(getSpecification(), products.getSpecification()) && Objects.equals(getBrand(), products.getBrand()) && Objects.equals(getQuantity(), products.getQuantity()) && Objects.equals(getCategory(), products.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getImages(), getProductName(), getMarketPrice(), getSellPrice(), getDimension(), getSpecification(), getBrand(), getQuantity(), getCategory());
    }
}
