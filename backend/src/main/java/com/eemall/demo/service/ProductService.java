package com.eemall.demo.service;

import com.eemall.demo.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface ProductService {


    /**
     * Find One Product
     * @param productId
     * @return
     */
    Product findOne(Long productId);

    /**
     * Find All selling products
     * @param pageable
     * @return
     */
    Page<Product> findAllInStock(Pageable pageable);

    /**
     * Find All
     * @param pageable
     * @return
     */
    Page<Product> findAll(Pageable pageable);

    /**
     * Find All Products In a Category
     * @param categoryType
     * @param pageable
     * @return
     */
    Page<Product> findAllInCategory(Integer categoryType, Pageable pageable);

    /**
     * All products that have keywords in product name
     * @param keyword
     * @param pageable
     * @return
     */
    Page<Product> findAllWithKeyword(String keyword, Pageable pageable);

    /**
     * Update
     * @param product
     * @return
     * @throws Exception
     */
    Product update(Product product) throws Exception;

    /**
     * Save
     * @param product
     * @return
     * @throws Exception
     */
    Product save(Product product) throws Exception;

    /**
     * Delete
     * @param productId
     * @throws Exception
     */
    void delete(Long productId) throws Exception;

    /**
     * Product Status: SOLD OUT
     * @param productId
     * @return
     * @throws Exception
     */
    Product SoldOut(Long productId) throws Exception;

    /**
     * Product status: On Sale
     * @param productId
     * @return
     * @throws Exception
     */
    Product onSale(Long productId) throws Exception;
}
