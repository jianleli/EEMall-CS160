package com.eemall.demo.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.eemall.demo.controller.CategoryController;
import com.eemall.demo.dto.ProductCategoryModel;
import com.eemall.demo.model.product.ProductCategory;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryModelAssembler
        extends RepresentationModelAssemblerSupport<ProductCategory, ProductCategoryModel> {
    public ProductCategoryModelAssembler() {
        super(CategoryController.class, ProductCategoryModel.class);
    }

    @Override
    public ProductCategoryModel toModel(ProductCategory entity) {
        ProductCategoryModel productCategoryModel = instantiateModel(entity);
        productCategoryModel.setId(entity.getCategoryType());
        productCategoryModel.setCategoryName(entity.getCategoryName());
        return productCategoryModel;
    }

    @Override
    public CollectionModel<ProductCategoryModel> toCollectionModel(Iterable<? extends ProductCategory> entities) {
        CollectionModel<ProductCategoryModel> productCategoryModels = super.toCollectionModel(entities);
        productCategoryModels.add(linkTo(methodOn(CategoryController.class).findAll()).withSelfRel());
        return productCategoryModels;
    }
}
