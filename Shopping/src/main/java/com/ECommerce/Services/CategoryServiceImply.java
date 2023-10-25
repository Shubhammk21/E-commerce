package com.ECommerce.Services;

import com.ECommerce.DTO.CategorySubDTO;
import com.ECommerce.DTO.Gender;
import com.ECommerce.Exception.CategoryException;
import com.ECommerce.Exception.CustomerException;
import com.ECommerce.Modules.Category;
import com.ECommerce.Repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryServiceImply implements CategoryService {

    @Autowired
    private CategoryRepo cr;

    @Override
    public List<Category> addCategory(List<Category> categories) throws CategoryException {
        for (Category category: categories){
            Optional<Category> optionalCategory= cr.findByCategoryNameAndSub(category.getCategoryName(), category.getSubCategory(),category.getCategoryType());
            if(optionalCategory.isPresent()){
                throw new CategoryException("§◙→ Category already present ←◙§");
            }else {
                category.setActive("Active");
            }
        }
        return cr.saveAll(categories);


    }

    @Override
    public List<Category> findAllCategoryName(Gender cat) throws CategoryException {
        List<Category> name= cr.findAllCategoryName(cat);
        if (name.isEmpty()){
            throw new CategoryException("♣█☻ Check Parameters ☻█♣");
        }else {
            return name;
        }
    }

    @Override
    public List<CategorySubDTO> findAllSubCategory(Gender cat, String catName) throws CategoryException {
        List<CategorySubDTO> name= cr.findAllSubCategory(cat,catName);
        if (name.isEmpty()){
            throw new CategoryException("♣█☻ Check Parameters ☻█♣");
        }else {
            return name;
        }
    }
}
