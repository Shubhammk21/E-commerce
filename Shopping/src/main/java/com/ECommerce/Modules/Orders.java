package com.ECommerce.Modules;

import com.ECommerce.DTO.ProductDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderID;

    @ManyToOne
    private Customers customer;

    @NotNull
    @NotBlank
    private String paymentType;
    @NotNull
    @NotBlank
    private Date orderdate;
    private Date diliveryDate;
    @NotNull
    @NotBlank
    private int total_Order_Amount;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProductDTO> orderProductList;

}
