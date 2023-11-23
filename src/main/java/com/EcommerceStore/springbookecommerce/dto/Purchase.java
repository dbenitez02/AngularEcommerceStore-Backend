package com.EcommerceStore.springbookecommerce.dto;

import com.EcommerceStore.springbookecommerce.entity.Address;
import com.EcommerceStore.springbookecommerce.entity.Customer;
import com.EcommerceStore.springbookecommerce.entity.Order;
import com.EcommerceStore.springbookecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;

    private Address shippingAddress;

    private Address billingAddress;

    private Order order;

    private Set<OrderItem> orderItems;
}
