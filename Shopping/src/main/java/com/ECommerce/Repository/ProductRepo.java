package com.ECommerce.Repository;

import com.ECommerce.Modules.Category;
import com.ECommerce.Modules.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Products,String> {
    @Query("select p from Products p where name LIKE :m ")
    public List<Products> search(@Param("m") String g);
    @Query("select p from Products p where brand LIKE :m ")
    public List<Products> searchByBrand(@Param("m")String g);
    @Query("select p from Products p where sellPrice <= :m ")
    public List<Products> priceLessThen(@Param("m")Double g);
   
}
