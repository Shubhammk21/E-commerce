package com.ECommerce.Modules;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "demo_sql")
    @GenericGenerator(name = "demo_sql",strategy = "com.ECommerce.Modules.StringSequenceGenerator",parameters = {
            @Parameter(name = StringSequenceGenerator.INCREMENT_PARAM,value="1"),
            @Parameter(name = StringSequenceGenerator.VALUE_PREFIX_PARAMETER, value = "PID_"),
            @Parameter(name = StringSequenceGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")

    })
    private int productId;

    @NotNull(message = "Enter Product Name")
    private String productName;

    @NotNull(message = "Enter Product Price")
    private double price;

    @NotNull(message = "Enter Product Dimension")
    private String dimension;

    @NotNull(message = "Enter Product Specification")
    private String specification;

    @NotNull(message = "Enter Product Manufacturer")
    private String manufacturer;

    @NotNull(message = "Enter Product Quantity")
    private Integer quantity;

//    @Embedded
//    private Category category;

    // @JsonIgnore
    // @ManyToOne(cascade = CascadeType.ALL)
    // private OrderDetails details;


}
