package com.eemall.demo.service;


import com.eemall.demo.dto.Purchase;
import com.eemall.demo.dto.PurchaseResponse;

public interface CheckoutService {

    /**
     * Place An Order
     * @param purchase
     * @return
     */
    PurchaseResponse placeOrder(Purchase purchase);

}
