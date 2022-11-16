package com.upcode.repository;

import com.upcode.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query("SELECT p FROM Product p WHERE CONCAT(p.name, ' ', p.id, ' ', p.quantity) LIKE %?1%")
    public List<Product> search(String keyword);

}
