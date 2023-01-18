package com.ECommerce.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductDTO {
    @Id
    private String productID;
    private String productName;
    private String brand;
    private double price;
    private String image;
    private float feedBack;

}
