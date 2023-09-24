package com.ECommerce.Repository;

import com.ECommerce.DTO.Gender;
import com.ECommerce.Exception.ProductException;
import com.ECommerce.Modules.Category;
import com.ECommerce.Modules.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Products,String> {

    @Query("select p from Products p where brand LIKE :m OR productName LIKE :m")
    public List<Products> topSearchProducts(@Param("m") String g);
//    @Query("select p from Products p where productName LIKE :m ")
//    public List<Products> search(@Param("m") String g);
    @Query("select p from Products p where brand LIKE :m ")
    public List<Products> searchByBrand(@Param("m")String g);
    @Query("select p from Products p where sellPrice <= :m ")
    public List<Products> priceLessThen(@Param("m")Double g);

    public List<Products> findByCategory(Category category);

    @Query("Select p from Products p inner JOIN Category c ON p.categoryId= c.categoryId where c.categoryType= :g AND c.categoryName= :cn AND c.subCategory= :sc AND p.brand= :b AND sellPrice BETWEEN :v1 AND :v2")
    public List<Products> filterProductRepo(@Param("g") Gender gender, @Param("cn") String catName, @Param("sc") String subCat, @Param("b") String brand, @Param("v1") int priceOne, @Param("v2") int priceTwo);
   
}
