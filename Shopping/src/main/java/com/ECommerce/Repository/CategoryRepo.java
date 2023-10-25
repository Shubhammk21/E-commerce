package com.ECommerce.Repository;

import com.ECommerce.DTO.CategorySubDTO;
import com.ECommerce.DTO.Gender;
import com.ECommerce.Modules.Category;
import com.ECommerce.Modules.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer> {

    @Query("select c from Category c where categoryName = :name and subCategory = :subCat and categoryType = :gType")
    public Optional<Category> findByCategoryNameAndSub(String name, String subCat, Gender gType);

    @Query("Select c from Category c where c.categoryType= :cat GROUP by c.categoryName ORDER BY c.categoryID ASC")
    public List<Category> findAllCategoryName(Gender cat);

    @Query("Select new com.ECommerce.DTO.CategorySubDTO(c.categoryID, c.subCategory) from Category c where c.categoryType= :cat and c.categoryName= :catName ORDER BY c.categoryID ASC")
    public List<CategorySubDTO> findAllSubCategory(Gender cat, String catName);

}
