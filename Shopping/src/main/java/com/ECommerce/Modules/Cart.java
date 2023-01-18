package com.ECommerce.Modules;

import com.ECommerce.DTO.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "demo_sql")
    @GenericGenerator(name = "demo_sql",strategy = "com.ECommerce.Modules.StringSequenceGenerator",parameters = {
            @org.hibernate.annotations.Parameter(name = StringSequenceGenerator.INCREMENT_PARAM,value="1"),
            @org.hibernate.annotations.Parameter(name = StringSequenceGenerator.VALUE_PREFIX_PARAMETER, value = "CartID_"),
            @org.hibernate.annotations.Parameter(name = StringSequenceGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")

    })
    private String cartId;

    @OneToMany
    private List<ProductDTO> productDTOS;
    private double total_Amount;

    @OneToOne
    private Customers customer;

}
