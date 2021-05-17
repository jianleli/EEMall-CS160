package com.eemall.demo.service.impl;

import com.eemall.demo.model.product.Product;
import com.eemall.demo.model.product.ProductStatus;
import com.eemall.demo.repository.ProductRepository;
import com.eemall.demo.service.CategoryService;
import com.eemall.demo.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImp implements ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImp.class);

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryService categoryService;

    /**
     * Get product information by using product ID
     * @param productId
     * @return
     */
    @Override
    public Product findOne(Long productId) {
        return productRepository.findByProductId(productId);
    }

    /**
     * Get all available products' information
     * @param pageable
     * @return
     */
    @Override
    public Page<Product> findAllInStock(Pageable pageable) {
        return productRepository.findAllByProductStatus(ProductStatus.IN_STOCK.getCode(),pageable);

    }

    /**
     * Get all products' information
     * @param pageable
     * @return
     */
    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAllByOrderByProductId(pageable);

    }

    @Override
    public Page<Product> findAllInCategory(Integer categoryType, Pageable pageable) {

        return productRepository.findAllByCategory(categoryType, pageable);
    }

    /**
     * find product by using keyword
     * @param keyword
     * @param pageable
     * @return
     */
    @Override
    public Page<Product> findAllWithKeyword(String keyword, Pageable pageable) {
        return productRepository.findByProductNameContainingIgnoreCase(keyword, pageable);
    }


    /**
     * update products information
     * @param product
     * @return
     * @throws Exception
     */
    @Override
    public Product update(Product product) throws Exception {
        categoryService.findByCategoryType(product.getCategory());
        logger.info(product.toString());
        if(product.getProductStatus() > 1) {
            throw new Exception("Status_error");
        }
        return productRepository.save(product);
    }

    /**
     * add new product to database
     * @param product
     * @return
     * @throws Exception
     */
    @Override
    public Product save(Product product) throws Exception {
        return update(product);
    }

    /**
     * delete product from database by using product ID
     * @param productId
     * @throws Exception
     */
    @Override
    public void delete(Long productId) throws Exception{
        Product product = findOne(productId);
        if (product == null) throw new Exception("Product not exist");
        productRepository.delete(product);
    }

    @Override
    public Product SoldOut(Long productId)throws Exception {
        Product product= findOne(productId);
        if(product == null) throw new Exception("product not exist");
        if (product.getProductStatus() == ProductStatus.OUT_OF_STOCK.getCode()) {
            throw new Exception("product not exist");

        }
        product.setProductStatus(ProductStatus.OUT_OF_STOCK.getCode());
        return productRepository.save(product);
    }

    @Override
    public Product onSale(Long productId) throws Exception {
        Product product = findOne(productId);
        if(product == null) throw new Exception("product not exist");

        if (product.getProductStatus() == ProductStatus.IN_STOCK.getCode()) {
            throw new Exception("product not exist");
        }
        product.setProductStatus(ProductStatus.IN_STOCK.getCode());
        return productRepository.save(product);
    }
}

