package com.eemall.demo.controller;

import com.eemall.demo.dto.Purchase;
import com.eemall.demo.dto.PurchaseResponse;
import com.eemall.demo.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {


    @Autowired
    CheckoutService checkoutService;

    /**
     * Checkout Service, placing an order
     * @param purchase
     * @return
     */
    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {
        PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);
        return purchaseResponse;
    }

}









