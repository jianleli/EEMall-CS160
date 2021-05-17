package com.eemall.demo.repository;

import com.eemall.demo.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ProductRepository extends JpaRepository<Product, String> {
    /**
     * Find Product by its ID
     * @param id
     * @return
     */
    Product findByProductId(Long id);

    /**
     * Find Product by its status ： onsale product
     * @param productStatus
     * @param pageable
     * @return
     */
    Page<Product> findAllByProductStatus(Integer productStatus, Pageable pageable);

    /**
     * Find product in one category
     * @param categoryType
     * @param pageable
     * @return
     */
    @Query("select p from Product p where p.categoryId.categoryType = ?1")
    Page<Product> findAllByCategory(Integer categoryType, Pageable pageable);

    /**
     *
     * @param pageable
     * @return
     */
    Page<Product> findAllByOrderByProductId(Pageable pageable);

    /**
     *
     * @param keyword
     * @param pageable
     * @return
     */
    Page<Product> findByProductNameContainingIgnoreCase(String keyword, Pageable pageable);
}
