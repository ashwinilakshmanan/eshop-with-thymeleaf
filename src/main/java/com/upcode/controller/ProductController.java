package com.upcode.controller;

import com.upcode.model.Product;
import com.upcode.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
     ProductService productService;


    @RequestMapping("/products")
    public String viewSearchProduct(ModelMap model, @Param("keyword") String keyword){
        List<Product> listProducts = productService.listAll(keyword);
        model.addAttribute("listItems",listProducts);
        model.addAttribute("keyword",keyword);
        return "products";

    }

    @GetMapping("/add_form")
    public String addProduct(ModelMap model){
        Product product=new Product();
        model.addAttribute("product",product);
        return  "add-product";
    }

    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("product") Product product){
        productService.addProduct(product);
        return  "redirect:/products";
    }


    @GetMapping("/edit/{id}")
    public String editProduct(Model model,@PathVariable("id") int id){
        model.addAttribute("product",productService.getProduct(id));
        return "edit-product";
    }

    @PostMapping("/update_product")
    public String updateProduct(Product product){
        productService.updateProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") int id){
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    @RequestMapping("/view/{id}")
    public ModelAndView showProduct(@PathVariable(name = "id") int id){
        ModelAndView mav=new ModelAndView("view-product");
        Optional<Product> product=productService.getProduct(id);
        mav.addObject("product",product);
        return mav;
    }






}
