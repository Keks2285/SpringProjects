package com.example.demoModels.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.demoModels.Repo.EmployeRepository;
import com.example.demoModels.Models.Employe;
import com.example.demoModels.Repo.ProductRepository;
import com.example.demoModels.Models.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
                             @RequestParam boolean isalive,
                              @Valid Employe employe,
                              Model model)
    {
       // if (isalive==null) isalive=false;
    //    Employe employe = new Employe(firstname,lastname,middlename, age, oklad,isalive);
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
                             @RequestParam Date creationdate,
                             @Valid Product product,
                             Model model)
    {
       // Product product = new Product(nameproduct,price,value, country, description,creationdate);
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








    @GetMapping("/employe/{id}")
    public String getEmployedata(@PathVariable(value = "id") long id,Model model)
    {
        Optional<Employe> employe = employeRepository.findById(id);
        ArrayList<Employe> employeRes = new ArrayList<>();
        employe.ifPresent(employeRes::add);
        model.addAttribute("employe", employeRes);
        if(!employeRepository.existsById(id)){
            return "redirect:/";
        }
        return "EmployeDetails";
    }
    @GetMapping("/product/{id}")
    public String getProductdata(@PathVariable(value = "id") long id,Model model)
    {
        Optional<Product> product = productRepository.findById(id);
        ArrayList<Product> productRes = new ArrayList<>();
        product.ifPresent(productRes::add);
        model.addAttribute("product", productRes);
        if(!productRepository.existsById(id)){
            return "redirect:/products";
        }
        return "ProductDetails";
    }


    @PostMapping("/employe/remove/{id}")
    public String deleteEmployedata(@PathVariable(value = "id") long id,Model model)
    {
        Employe employe = employeRepository.findById(id).orElseThrow();
        employeRepository.delete(employe);
        return "redirect:/";
    }

    @PostMapping("/product/{id}/remove/")
    public String deleteProductdata(@PathVariable(value = "id") long id,Model model)
    {
        Product product = productRepository.findById(id).orElseThrow();
        productRepository.delete(product);
        return "redirect:/products";
    }

    @GetMapping("/employe/edit/{id}")
    public String editEmployedataPage(@PathVariable(value = "id") long id,Model model)
    {

        Optional<Employe> employe = employeRepository.findById(id);
        ArrayList<Employe> employeRes = new ArrayList<>();
        employe.ifPresent(employeRes::add);
        model.addAttribute("employe", employeRes);
        if(!employeRepository.existsById(id)){
            return "redirect:/";
        }
        return "EmployeEdit";
    }

    @GetMapping("/product/edit/{id}")
    public String editProductdataPage(@PathVariable(value = "id") long id,Model model)
    {

        Optional<Product> product = productRepository.findById(id);
        ArrayList<Product> productRes = new ArrayList<>();
        product.ifPresent(productRes::add);
        model.addAttribute("product", productRes);
        if(!productRepository.existsById(id)){
            return "redirect:/products";
        }
        return "ProductEdit";
    }

    @PostMapping("/employe/edit/{id}")
    public String editEmployedata(@PathVariable(value = "id") long id,
                                  @RequestParam String firstname,
                                  @RequestParam String lastname,
                                  @RequestParam String middlename,
                                  @RequestParam int age,
                                  @RequestParam double oklad,
                                  @RequestParam(defaultValue = "false"
                                  ) boolean isalive ,Model  model)
    {
        Employe employe = employeRepository.findById(id).orElseThrow();
        employe.setFirstname(firstname);
        employe.setAge(age);
        employe.setLastname(lastname);
        employe.setMiddlename(middlename);
        employe.setOklad(oklad);
        employe.setIsalive(isalive);
        employeRepository.save(employe);
        return "redirect:/";
    }
    @PostMapping("/product/edit/{id}")
    public String editProductdata(@PathVariable(value = "id") long id,
                                  @RequestParam String nameproduct,
                                  @RequestParam double price,
                                  @RequestParam int value,
                                  @RequestParam String country,
                                  @RequestParam String description,
                                  @RequestParam  Date creationdate,Model  model)
    {
        Product product = productRepository.findById(id).orElseThrow();
        product.setCountry(country);
        product.setPrice(price);
        product.setValue(value);
        product.setNameproduct(nameproduct);
        product.setDescription(description);
        product.setCreationdate(creationdate);
        productRepository.save(product);
        return "redirect:/products";
    }
}