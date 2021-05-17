package com.eemall.demo.model.product;

import lombok.Getter;


@Getter
public enum ProductStatus implements Code{
    IN_STOCK(0, "In Stock"), OUT_OF_STOCK(1, "Out of stock");
    private Integer code;
    private String message;

    ProductStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getStatus(Integer code) {

        for (ProductStatus statusEnum : ProductStatus.values()) {
            if (statusEnum.getCode().equals(code)) return statusEnum.message;
        }
        return "";
    }

    public Integer getCode() {
        return code;
    }
}
