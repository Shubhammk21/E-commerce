package com.ECommerce.Controllers;

import com.ECommerce.Exception.CategoryException;
import com.ECommerce.Exception.ProductException;
import com.ECommerce.Modules.Images;
import com.ECommerce.Modules.Products;
import com.ECommerce.Services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController("/Products")
public class ProductsController {

    @Autowired
    private ProductServices ps;

    @PostMapping("/Add/Product")
    public ResponseEntity<Products> addProduct(@Valid @RequestBody Products product, @RequestParam(required = false) Integer categoryId)throws ProductException, CategoryException{

        Products addedProduct= ps.addProduct(product,categoryId);

        return new ResponseEntity<Products>(addedProduct, HttpStatus.CREATED);
    }
    @PostMapping("/Add/Product/{productId}/image")
    public ResponseEntity<Products> addProductImg(@Valid @PathVariable("productId") String productId, @RequestBody List<Images> image)throws ProductException{

        Products addedImages= ps.addProductImg(productId,image);

        return new ResponseEntity<Products>(addedImages, HttpStatus.OK);
    }

    @GetMapping("/List")
    public ResponseEntity<List<Products>> viewProducts() throws ProductException{

        List<Products> productsList= ps.viewProducts();

        return new ResponseEntity<List<Products>>(productsList, HttpStatus.OK);
    }

    @GetMapping("/List/Search/{m}")
    public ResponseEntity<List<Products>> searchProducts(@PathVariable("m")String m) throws ProductException{

        List<Products> productsList= ps.searchProducts(m);

        return new ResponseEntity<List<Products>>(productsList, HttpStatus.OK);
    }

    @GetMapping("/List/Category/{catId}")
    public ResponseEntity<List<Products>> searchByCategory(@PathVariable Integer catId) throws ProductException,CategoryException{

        List<Products> productsListByCat= ps.searchByCategory(catId);

        return new ResponseEntity<List<Products>>(productsListByCat, HttpStatus.OK);
    }

    @GetMapping("/List/Brand/{m}")
    public ResponseEntity<List<Products>> searchByBrand(@PathVariable String m) throws ProductException{

        List<Products> productsList= ps.searchByBrand(m);

        return new ResponseEntity<List<Products>>(productsList, HttpStatus.OK);

    }

    @GetMapping("/List/UnderPrice/{m}")
    public ResponseEntity<List<Products>> searchByPriceLessThan(@PathVariable Double m) throws ProductException{

        List<Products> productsList= ps.searchByPriceLessThan(m);

        return new ResponseEntity<List<Products>>(productsList, HttpStatus.OK);

    }

}
