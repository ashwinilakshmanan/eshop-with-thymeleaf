package com.upcode.controller;

import com.upcode.model.Product;
import com.upcode.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
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
    public String displayAllProducts(Model model){
        model.addAttribute("products",productService.getAllProducts());
        return "products";
    }

    @GetMapping("/add_form")
    public String addProduct(Model model){
        Product product=new Product();
        model.addAttribute("product",product);
        return  "add-product";
    }

    @PostMapping("/create_product")
    public String createProduct(@ModelAttribute Product product, HttpSession session){
        productService.addProduct(product);
        session.setAttribute("msg","Product added successfully...");
        return "redirect:/add_form";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(Model model,@PathVariable("id") int id){
        model.addAttribute("product",productService.getProduct(id));
        return "edit-product";
    }

    @PostMapping("/update_product")
    public String updateProduct(Product product){
        productService.updateProduct(product);
        return "redirect:view/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id){
        productService.deleteProduct(id);
        return "redirect:view/products";
    }


//    @RequestMapping("/products/{id}")
//    public Optional<Product> getProduct(@PathVariable int id) {
//        return productService.getProduct(id);
//    }
//
//    @RequestMapping(method = RequestMethod.POST, value = "/products")
//    public void addProduct(@RequestBody Product product) {
//        productService.addProduct(product);
//    }
//
//    @RequestMapping(method = RequestMethod.PUT, value = "/products/{id}")
//    public void updateProduct(@RequestBody Product product, @PathVariable int id) {
//        productService.updateProduct( product);
//    }
//
//    @RequestMapping(method = RequestMethod.DELETE, value = "/products/{id}")
//    public void deleteProduct(@PathVariable int id) {
//        productService.deleteProduct(id);
//    }
}
