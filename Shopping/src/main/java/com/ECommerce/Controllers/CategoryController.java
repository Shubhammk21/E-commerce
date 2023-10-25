package com.ECommerce.Controllers;

import com.ECommerce.DTO.CategorySubDTO;
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
    @PostMapping("/Products/add/categoryList")
    public ResponseEntity<List<Category>> addCategory(@Valid @RequestBody List<Category> categories) throws CategoryException {

        List<Category> cat= cs.addCategory(categories);

        return new ResponseEntity<List<Category>>(cat, HttpStatus.ACCEPTED);
    }
    @GetMapping("/Category/list/CategoryName/{cat}")
    public ResponseEntity<List<Category>> listOfAllCategoryName(@PathVariable Gender cat) throws CategoryException{

        List<Category> names= cs.findAllCategoryName(cat);

        return new ResponseEntity<List<Category>>(names, HttpStatus.OK);
    }
    @GetMapping("/Category/list/SubCategory/{cat}/{catName}")
    public ResponseEntity<List<CategorySubDTO>> listOfAllSubCategory(@PathVariable Gender cat, @PathVariable String catName) throws CategoryException{

        List<CategorySubDTO> names= cs.findAllSubCategory(cat,catName);

        return new ResponseEntity<List<CategorySubDTO>>(names, HttpStatus.OK);
    }

}
