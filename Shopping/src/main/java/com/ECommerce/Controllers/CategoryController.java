package com.ECommerce.Controllers;

import com.ECommerce.DTO.Gender;
import com.ECommerce.Exception.CategoryException;
import com.ECommerce.Exception.ProductException;
import com.ECommerce.Modules.Category;
import com.ECommerce.Modules.Products;
import com.ECommerce.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
public class CategoryController {

    @Autowired
    public CategoryService cs;
    @PostMapping("/Products/add/category")
    public ResponseEntity<Category> addCategory(@Valid @RequestBody Category category) throws CategoryException {

        Category cat= cs.addCategory(category);

        return new ResponseEntity<Category>(cat, HttpStatus.ACCEPTED);
    }
    @GetMapping("/Category/list/CategoryName/{cat}")
    public ResponseEntity<Set<String>> listOfAllCategoryName(@PathVariable Gender cat) throws CategoryException{

        Set<String> names= cs.findAllCategoryName(cat);

        return new ResponseEntity<Set<String>>(names, HttpStatus.OK);
    }
    @GetMapping("/Category/list/SubCategory/{catName}")
    public ResponseEntity<List<String>> listOfAllSubCategory(@PathVariable String catName) throws CategoryException{

        List<String> names= cs.findAllSubCategory(catName);

        return new ResponseEntity<List<String>>(names, HttpStatus.OK);
    }

}
