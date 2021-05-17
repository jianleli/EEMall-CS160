package com.eemall.demo.repository;

import com.eemall.demo.model.product.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<ProductCategory, Integer> {
    /**
     *
     * @param categoryTypes
     * @return
     */
    List<ProductCategory> findByCategoryTypeInOrderByCategoryTypeAsc(List<Integer> categoryTypes);

    /**
     * All category
     * @return
     */
    List<ProductCategory> findAll();

    /**
     * One Specify category
     * @param categoryType
     * @return
     */
    ProductCategory findByCategoryType(Integer categoryType);
}
