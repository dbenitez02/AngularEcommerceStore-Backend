package com.EcommerceStore.springbookecommerce.dao;

import com.EcommerceStore.springbookecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
