package com.eemall.demo.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.eemall.demo.controller.ProductController;
import com.eemall.demo.dto.ProductModel;
import com.eemall.demo.model.product.Product;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;


@Component
public class ProductModelAssembler extends RepresentationModelAssemblerSupport<Product, ProductModel> {
    public ProductModelAssembler() {
        super(ProductController.class, ProductModel.class);
    }
    @Override
    public ProductModel toModel(Product entity) {
        ProductModel productModel = instantiateModel(entity);

        productModel.add(linkTo(methodOn(ProductController.class).showOne(entity.getProductId())).withSelfRel());

        productModel.setId(entity.getProductId());
        productModel.setName(entity.getProductName());
        productModel.setSku(entity.getSku());
        productModel.setDescription(entity.getProductDescription());
        productModel.setUnitPrice(entity.getUnitPrice());
        productModel.setImageUrl(entity.getImageUrl());
        productModel.setActive(entity.isActive());
        productModel.setUnitsInStock(entity.getUnitsInStock());
        productModel.setDateCreated(entity.getDateCreated());
        productModel.setLastUpdated(entity.getLastUpdated());
        productModel.setCategoryName(entity.getCategoryId().getCategoryName());

        return productModel;

    }

}
