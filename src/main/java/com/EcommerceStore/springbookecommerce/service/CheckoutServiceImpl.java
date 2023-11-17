package com.EcommerceStore.springbookecommerce.service;

import com.EcommerceStore.springbookecommerce.dao.CustomerRepository;
import com.EcommerceStore.springbookecommerce.dto.Purchase;
import com.EcommerceStore.springbookecommerce.dto.PurchaseResponse;
import com.EcommerceStore.springbookecommerce.entity.Customer;
import com.EcommerceStore.springbookecommerce.entity.Order;
import com.EcommerceStore.springbookecommerce.entity.OrderItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private CustomerRepository customerRepository;

    public CheckoutServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        Order order = purchase.getOrder();

        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        Set<OrderItem> orderItems = purchase.getOrderItem();
        orderItems.forEach(item -> order.add(item));

        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        Customer customer = purchase.getCustomer();
        customer.add(order);

        customerRepository.save(customer);
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        // Generate random uuid
        return UUID.randomUUID().toString();
    }
}
