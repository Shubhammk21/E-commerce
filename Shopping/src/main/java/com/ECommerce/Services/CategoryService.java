package com.ECommerce.Services;

import com.ECommerce.DTO.CategorySubDTO;
import com.ECommerce.DTO.Gender;
import com.ECommerce.Exception.CategoryException;
import com.ECommerce.Modules.Category;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface CategoryService {

    public List<Category> addCategory(List<Category> categories) throws CategoryException;
    public List<Category> findAllCategoryName(Gender cat) throws CategoryException;
    public List<CategorySubDTO> findAllSubCategory(Gender cat, String catName) throws CategoryException;
}
