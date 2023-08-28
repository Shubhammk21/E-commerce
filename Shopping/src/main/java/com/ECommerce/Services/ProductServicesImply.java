package com.ECommerce.Services;


import com.ECommerce.Exception.CategoryException;
import com.ECommerce.Exception.ProductException;
import com.ECommerce.Modules.Category;
import com.ECommerce.Modules.Images;
import com.ECommerce.Modules.Products;
import com.ECommerce.Repository.CategoryRepo;
import com.ECommerce.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductServicesImply implements ProductServices{

    @Autowired
    private ProductRepo pr;

    @Autowired
    private CategoryRepo cr;

    @Override
    public Products addProduct(Products product, Integer categoryId) throws ProductException, CategoryException {
        Optional<Category> opCat= cr.findById(categoryId);
        if(opCat.isEmpty()){
            throw new CategoryException("§◙→ Category Not found ←◙§");
        }else {
            product.setCategory(opCat.get());
            product.setAddDateTime(LocalDateTime.now());
            return pr.save(product);
        }

    }

    @Override
    public Products addProductImg(String productId, List<Images> image) throws ProductException {

        Optional<Products> op= pr.findById(productId);
        if(op.isEmpty()){
            throw new ProductException("§◙→ Product Not found ←◙§");
        }else{
            Set<Images> imagesSet= op.get().getImages();
            imagesSet.addAll(image);
            op.get().setImages(imagesSet);
            return pr.save(op.get());
        }

    }

    @Override
    public List<Products> viewProducts() throws ProductException {
        return pr.findAll();
    }

    @Override
    public List<Products> searchProducts(String m) throws ProductException {
        String sqlModString= "%"+m+"%";
        List<Products> productsList= pr.search(sqlModString);
        if(productsList.isEmpty()){
            throw new ProductException("§◙→ Product Not found ←◙§");
        }
        return productsList;
    }

    @Override
    public List<Products> searchByCategory(Integer catId) throws ProductException, CategoryException {
        Optional<Category> oCat= cr.findById(catId);
        if(oCat.isEmpty()){
            throw new CategoryException("§◙→ Category Not Match ←◙§");
        }else {
            List<Products>productsList= pr.findByCategory(oCat.get());
            if (productsList.size()==0){
                throw new ProductException("§◙→ Product Not found ←◙§");
            }else{
                return productsList;
            }
        }
    }

    @Override
    public List<Products> searchByBrand(String m) throws ProductException {
        String sqlModString= "%"+m+"%";
        List<Products> productsList= pr.searchByBrand(sqlModString);
        if(productsList.isEmpty()){
            throw new ProductException("§◙→ Product Not found ←◙§");
        }else{
            return productsList;
        }

    }

    @Override
    public List<Products> searchByPriceLessThan(Double m) throws ProductException {
        List<Products> productsList= pr.priceLessThen(m);
        if(productsList.isEmpty()){
            throw new ProductException("§◙→ Product Not found ←◙§");
        }else{
            return productsList;
        }
    }


}
