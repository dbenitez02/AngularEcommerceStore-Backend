package com.EcommerceStore.springbookecommerce.service;

import com.EcommerceStore.springbookecommerce.dto.Purchase;
import com.EcommerceStore.springbookecommerce.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
