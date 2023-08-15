package com.ECommerce.Repository;

import com.ECommerce.DTO.Gender;
import com.ECommerce.Modules.Category;
import com.ECommerce.Modules.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer> {

    @Query("select c from Category c where categoryName = :name and subCategory = :subCat and categoryType = :gType")
    public Optional<Category> findByCategoryNameAndSub(String name, String subCat, Gender gType);
}
