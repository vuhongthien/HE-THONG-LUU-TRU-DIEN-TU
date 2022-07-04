package com.example.Web.repository;

import com.example.Web.model.Product;
import com.example.Web.service.ProductService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
 @Query(value = "select * from product where name like %:key% or price1 like %:key%", nativeQuery = true)
    List<Product> findByKey(@Param("key") String key);

}
