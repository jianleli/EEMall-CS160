package com.eemall.demo.service;

import com.eemall.demo.model.product.ProductCategory;

import java.util.List;

public interface CategoryService {
    /**
     * Fina All
     * @return
     */
    List<ProductCategory> findAll();

    /**
     *  Find by Category Type
     * @param categoryType
     * @return
     * @throws Exception
     */
    ProductCategory findByCategoryType(Integer categoryType) throws Exception;

    /**
     *
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory> findByCategoryIn(List<Integer> categoryTypeList);

    /**
     * Save
     * @param productCategory
     * @return
     */
    ProductCategory save(ProductCategory productCategory);
}
