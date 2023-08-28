package com.ECommerce.Services;

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
    public Category addCategory(Category category) throws CategoryException {
        Optional<Category> optionalCategory= cr.findByCategoryNameAndSub(category.getCategoryName(), category.getSubCategory(),category.getCategoryType());
        if(optionalCategory.isPresent()){
            throw new CategoryException("§◙→ Category already present ←◙§");
        }else{
            category.setActive("Active");
            return cr.save(category);
        }

    }

    @Override
    public Set<String> findAllCategoryName(Gender cat) throws CategoryException {
        Set<String>name= cr.findAllCategoryName(cat);
        if (name.isEmpty()){
            throw new CategoryException("♣█☻ Check Parameters ☻█♣");
        }else {
            return name;
        }
    }

    @Override
    public List<String> findAllSubCategory(String catName) throws CategoryException {
        List<String>name= cr.findAllSubCategory(catName);
        if (name.isEmpty()){
            throw new CategoryException("♣█☻ Check Parameters ☻█♣");
        }else {
            return name;
        }
    }
}
