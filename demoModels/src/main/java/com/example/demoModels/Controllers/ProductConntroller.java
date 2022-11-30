package com.example.demoModels.Controllers;

import com.example.demoModels.Models.Employe;
import com.example.demoModels.Models.Product;
import com.example.demoModels.Repo.EmployeRepository;
import com.example.demoModels.Repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
//@PreAuthorize("hasAnyAuthority('EMPLOYER', 'ADMIN', 'USER')")
public class ProductConntroller {
    @Autowired
    private EmployeRepository employeRepository;
    @Autowired
    private ProductRepository productRepository;

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'EMPLOYER')")
    @GetMapping("/products")
    public String productsPage(Model model)
    {
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products",products);
        return "Products";
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYER')")
    @GetMapping("/product/add")
    public String prooductAddView(Product product)
    {
        return "ProductCreate";
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYER')")
    @PostMapping("/product/add")
    public String productAdd(@Valid Product product,
                             BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){return "ProductCreate";}
        // Product product = new Product(nameproduct,price,value, country, description,creationdate);
        productRepository.save(product);
        return "redirect:/products";
    }




    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'EMPLOYER')")
    @GetMapping("/product/filter")
    public String productFilter(Model model)
    {
        return "FilterProduct";
    }
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'EMPLOYER')")
    @PostMapping("/product/filterContains/result")
    public String productResultContains(@RequestParam String nameproduct, Model model)
    {
        List<Product> result = productRepository.findByNameproductContains(nameproduct);
        model.addAttribute("result", result);
        return "FilterProduct";
    }
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'EMPLOYER')")
    @PostMapping("/product/filterAll/result")
    public String productResultAll(@RequestParam String nameproduct, Model model)
    {
        List<Product> result = productRepository.findByNameproduct(nameproduct);
        model.addAttribute("result", result);
        return "FilterProduct";
    }






    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'EMPLOYER')")
    @GetMapping("/product/{id}")
    public String getProductdata(@PathVariable(value = "id") long id, Model model)
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



    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'EMPLOYER')")
    @PostMapping("/product/{id}/remove/")
    public String deleteProductdata(@PathVariable(value = "id") long id,Model model)
    {
        Product product = productRepository.findById(id).orElseThrow();
        productRepository.delete(product);
        return "redirect:/mainproducts";
    }


    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'EMPLOYER')")
    @GetMapping("/product/edit/{id}")
    public String editProductdataPage(@PathVariable(value = "id") long id, Model model)
    {

//        Optional<Product> product = productRepository.findById(id);
//        ArrayList<Product> productRes = new ArrayList<>();
//        product.ifPresent(productRes::add);
//        model.addAttribute("product", productRes);
        Product res = productRepository.findById(id).orElseThrow();
        model.addAttribute("product",res);
        if(!productRepository.existsById(id)){
            return "redirect:/products";
        }
        return "ProductEdit";
    }

    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'EMPLOYER')")
    @PostMapping("/product/edit/{id}")
    public String editProductdata(@PathVariable(value = "id")  long id, @Valid Product product ,   BindingResult bindingResult)
    {
//        Product product = productRepository.findById(id).orElseThrow();
//        product.setCountry(country);
//        product.setPrice(price);
//        product.setValue(value);
//        product.setNameproduct(nameproduct);
//        product.setDescription(description);
//        product.setCreationdate(creationdate);
        if(bindingResult.hasErrors()) return "ProductEdit";
        productRepository.save(product);
        return "redirect:/products";
    }
}
