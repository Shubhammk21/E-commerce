package com.ECommerce.Services;

import com.ECommerce.DTO.Gender;
import com.ECommerce.Exception.CategoryException;
import com.ECommerce.Exception.ProductException;
import com.ECommerce.Modules.Category;
import com.ECommerce.Modules.Images;
import com.ECommerce.Modules.Products;

import java.util.List;

public interface ProductServices {
    public Products addProduct(Products product, int categoryId)throws ProductException, CategoryException;
    public Products addProductImg(String productId, List<Images> image)throws ProductException;
    public List<Products> viewProducts() throws ProductException;
    public List<Products> searchProducts(String m) throws ProductException;
    public List<Products> searchByCategory(Integer catId) throws ProductException,CategoryException;
    public List<Products> searchByBrand(String m) throws ProductException;
    public List<Products> searchByPriceLessThan(Double m) throws ProductException;
    public List<Products> filterProduct(Gender gender, String catType, String catName, String brand, int priceOne, int priceTwo) throws ProductException;


}
