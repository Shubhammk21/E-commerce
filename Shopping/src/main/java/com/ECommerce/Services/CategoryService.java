package com.ECommerce.Services;

import com.ECommerce.DTO.Gender;
import com.ECommerce.Exception.CategoryException;
import com.ECommerce.Modules.Category;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface CategoryService {

    public Category addCategory(Category category) throws CategoryException;
    public Set<String> findAllCategoryName(Gender cat) throws CategoryException;
    public List<Category> findAllSubCategory(Gender cat, String catName) throws CategoryException;
}
