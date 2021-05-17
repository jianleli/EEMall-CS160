package com.eemall.demo.controller;

import com.eemall.demo.assemblers.ProductModelAssembler;
import com.eemall.demo.dto.ProductModel;
import com.eemall.demo.model.product.Product;
import com.eemall.demo.service.CategoryService;
import com.eemall.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class ProductController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    ProductModelAssembler productModelAssembler;
    @Autowired
    private PagedResourcesAssembler<Product> pagedResourcesAssembler;



    /**
     * Get All Product Info
     * @param pageable
     * @return
     */
    @GetMapping("/product")
    public ResponseEntity<PagedModel<ProductModel>> findAll(Pageable pageable) {
        Page<Product> products = productService.findAll(pageable);
        PagedModel<ProductModel> productModels = pagedResourcesAssembler.toModel(products, productModelAssembler);
        return new ResponseEntity<>(productModels, HttpStatus.OK);
    }

    /**
     * Search a product by entering Key Word
     * @param keyword
     * @param pageable
     * @return
     */
    @GetMapping("/product/search")
    public ResponseEntity<PagedModel<ProductModel>> findAll(@RequestParam String keyword, Pageable pageable) {
        Page<Product> products = productService.findAllWithKeyword(keyword, pageable);
        PagedModel<ProductModel> productModels = pagedResourcesAssembler.toModel(products, productModelAssembler);
        return new ResponseEntity<>(productModels, HttpStatus.OK);
    }

    /**
     * Get one product By productID
     * @param productId
     * @return
     */
    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductModel> showOne(@PathVariable("productId") Long productId) {
        return Optional.ofNullable(productService.findOne(productId))
                .map(productModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    /**
     * Admin Model : Add New Product
     * @param product
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @PostMapping("/product/new")
    public ResponseEntity create(@Valid @RequestBody Product product,
                                 BindingResult bindingResult) throws Exception {
        Product productIdExists = productService.findOne(product.getProductId());
        if (productIdExists != null) {
            bindingResult
                    .rejectValue("productId", "error.product",
                            "There is already a product with the code provided");
        }
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult);
        }
        return ResponseEntity.ok(productService.save(product));
    }

    /**
     * Admin Model: Edit One Product
     * @param productId
     * @param product
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @PutMapping("/product/{id}/edit")
    public ResponseEntity edit(@PathVariable("id") Long productId,
                               @Valid @RequestBody Product product,
                               BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult);
        }
        if (!productId.equals(product.getProductId())) {
            return ResponseEntity.badRequest().body("Id Not Matched");
        }

        return ResponseEntity.ok(productService.update(product));
    }

    /**
     * Admin Model: Delete One Product
     * @param productId
     * @return
     * @throws Exception
     */
    @DeleteMapping("/product/{id}/delete")
    public ResponseEntity delete(@PathVariable("id") Long productId) throws Exception {
        productService.delete(productId);
        return ResponseEntity.ok().build();
    }
}
