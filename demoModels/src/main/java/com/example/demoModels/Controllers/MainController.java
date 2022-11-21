package com.example.demoModels.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.demoModels.Repo.EmployeRepository;
import com.example.demoModels.Models.Employe;
import com.example.demoModels.Repo.ProductRepository;
import com.example.demoModels.Models.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private EmployeRepository employeRepository;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public String employerMain(Model model)
    {
        Iterable<Employe> employers = employeRepository.findAll();
        model.addAttribute("employers",employers);
        return "MainPage";
    }

    @GetMapping("/products")
    public String productsPage(Model model)
    {
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products",products);
        return "Products";
    }

    @PostMapping("/employe/add")
    public String employeAdd(@RequestParam String firstname,
                              @RequestParam String lastname,
                              @RequestParam String middlename,
                              @RequestParam int age,
                              @RequestParam double oklad,
                              Model model)
    {
        Employe employe = new Employe(firstname,lastname,middlename, age, oklad);
        employeRepository.save(employe);
        return "redirect:/";
    }



    @GetMapping("/employe/add")
    public String employeAddView(Model model)
    {
        return "EmployeCreate";
    }

    @GetMapping("/product/add")
    public String prooductAddView(Model model)
    {
        return "ProductCreate";
    }

    @PostMapping("/product/add")
    public String productAdd(@RequestParam String nameproduct,
                             @RequestParam double price,
                             @RequestParam int value,
                             @RequestParam String country,
                             @RequestParam String description,
                             Model model)
    {
        Product product = new Product(nameproduct,price,value, country, description);
        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/employe/filter")
    public String employeFilter(Model model)
    {
        return "FilterEmploye";
    }

    @GetMapping("/product/filter")
    public String productFilter(Model model)
    {
        return "FilterProduct";
    }

    @PostMapping("/employe/filterContains/result")
    public String employeResultContains(@RequestParam String firstname, Model model)
    {
        List<Employe> result = employeRepository.findByFirstnameContains(firstname);
        model.addAttribute("result", result);
        return "FilterEmploye";
    }
    @PostMapping("/employe/filterAll/result")
    public String employeResultAll(@RequestParam String firstname, Model model)
    {
        List<Employe> result = employeRepository.findByFirstname(firstname);
        model.addAttribute("result", result);
        return "FilterEmploye";
    }


    @PostMapping("/product/filterContains/result")
    public String productResultContains(@RequestParam String nameproduct, Model model)
    {
        List<Product> result = productRepository.findByNameproductContains(nameproduct);
        model.addAttribute("result", result);
        return "FilterProduct";
    }
    @PostMapping("/product/filterAll/result")
    public String productResultAll(@RequestParam String nameproduct, Model model)
    {
        List<Product> result = productRepository.findByNameproduct(nameproduct);
        model.addAttribute("result", result);
        return "FilterProduct";
    }
}