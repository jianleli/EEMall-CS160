package com.eemall.demo.controller;

import com.eemall.demo.assemblers.ProductCategoryModelAssembler;
import com.eemall.demo.assemblers.ProductModelAssembler;
import com.eemall.demo.dto.ProductCategoryModel;
import com.eemall.demo.dto.ProductModel;
import com.eemall.demo.model.product.ProductCategory;
import com.eemall.demo.model.product.Product;
import com.eemall.demo.service.CategoryService;
import com.eemall.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    ProductModelAssembler productModelAssembler;
    @Autowired
    private PagedResourcesAssembler<Product> pagedResourcesAssembler;

    @Autowired
    private ProductCategoryModelAssembler productCategoryModelAssembler;

    /**
     * Find All by Category
     * @return
     */
    @GetMapping("/categories")
    public ResponseEntity<CollectionModel<ProductCategoryModel>> findAll() {
        List<ProductCategory> productCategories = (List<ProductCategory>)categoryService.findAll();
        return new ResponseEntity<>(productCategoryModelAssembler.toCollectionModel(productCategories), HttpStatus.OK);
    }

    /**
     * Find All product by Specify category type
     * @param categoryType
     * @param pageable
     * @return
     */
    @GetMapping("/categories/{type}")
    public ResponseEntity<PagedModel<ProductModel>> showOne(@PathVariable("type") Integer categoryType, Pageable pageable) {
        Page<Product> productsInCategory = productService.findAllInCategory(categoryType, pageable);
        PagedModel<ProductModel> productModels = pagedResourcesAssembler.toModel(productsInCategory, productModelAssembler);
        return new ResponseEntity<>(productModels, HttpStatus.OK);
    }
}
