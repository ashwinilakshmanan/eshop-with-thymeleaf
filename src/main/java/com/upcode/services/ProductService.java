package com.upcode.services;

import com.upcode.model.Product;
import com.upcode.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    //get all products
    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    //get products by id
    public Optional<Product> getProduct(int id) {
        return productRepository.findById(id);
    }

    //add products
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    //update products
    public void updateProduct(int id, Product prod) {
        Optional<Product> product;
        product = productRepository.findById(id);
        if (product.isEmpty()) {
            System.out.println("item not found");
            return;
        }
        Product existingProduct = product.get();
        existingProduct.setName(prod.getName());
        existingProduct.setQuantity(prod.getQuantity());

        productRepository.save(existingProduct);

    }

    //delete products
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }


}
