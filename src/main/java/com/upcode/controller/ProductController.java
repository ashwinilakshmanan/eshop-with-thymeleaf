package com.upcode.controller;

import com.upcode.model.Product;
import com.upcode.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;


    @RequestMapping("/products")
    @ResponseBody
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @RequestMapping("view/products")
    public String displayProducts(Model model){
        model.addAttribute("products",getAllProducts());
        return "products";
    }


    @RequestMapping("/products/{id}")
    public Optional<Product> getProduct(@PathVariable int id) {
        return productService.getProduct(id);

    }

    @RequestMapping(method = RequestMethod.POST, value = "/products")
    public void addProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/products/{id}")
    public void updateProduct(@RequestBody Product product, @PathVariable int id) {
        productService.updateProduct(id, product);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/products/{id}")
    public void deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
    }
}
