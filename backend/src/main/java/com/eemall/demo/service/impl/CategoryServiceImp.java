package com.eemall.demo.service.impl;

import com.eemall.demo.model.product.ProductCategory;
import com.eemall.demo.repository.CategoryRepository;
import com.eemall.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<ProductCategory> findAll() {
        List<ProductCategory> res = categoryRepository.findAll();
        return res;
    }

    @Override
    public ProductCategory findByCategoryType(Integer categoryType) throws Exception {
        ProductCategory res = categoryRepository.findByCategoryType(categoryType);
        if(res == null) throw new Exception("CATEGORY_NOT_FOUND");
        return res;
    }

    @Override
    public List<ProductCategory> findByCategoryIn(List<Integer> categoryTypeList) {
        List<ProductCategory> res = categoryRepository.findByCategoryTypeInOrderByCategoryTypeAsc(categoryTypeList);
        return res;
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return categoryRepository.save(productCategory);
    }
}
