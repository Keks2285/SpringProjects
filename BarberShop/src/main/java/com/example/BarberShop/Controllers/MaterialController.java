package com.example.BarberShop.Controllers;

import com.example.BarberShop.Models.Material;
import com.example.BarberShop.Repo.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class MaterialController {
    @Autowired
    private MaterialRepository materialRepository;

    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'STOCKER')")
    @GetMapping("/materials")
    public String MainPageGet(Model model)
    {
        Material material = new Material();
        Iterable<Material> materials = materialRepository.findAll();
        model.addAttribute("materials",materials);
        model.addAttribute("material",material);
        return "MaterialPages/MaterialList";
    }
    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'STOCKER')")
    @PostMapping("/materials/filter")
    public String providersSearch(@RequestParam String name, Model model)
    {
        Material material = new Material();
        List<Material> materials = materialRepository.findByNameContains(name);
        model.addAttribute("materials", materials);
        model.addAttribute("material",material);
        return "MaterialPages/MaterialList";
    }
    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'STOCKER')")
    @PostMapping("/materials/add")
    public String proiderAdd(@Valid Material material,
                             BindingResult bindingResult, Model model)
    {
        model.addAttribute("materials", materialRepository.findAll());
        if(materialRepository.findByName(material.getName())!=null) //не выводит сообщение
            bindingResult.addError(new ObjectError("name",  "Уже существует атериал с таким названием"));

        if(bindingResult.hasErrors()){return "MaterialPages/MaterialList";}
        // Product product = new Product(nameproduct,price,value, country, description,creationdate);
        materialRepository.save(material);
        return "redirect:/materials";
    }

    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'STOCKER')")
    @GetMapping("/material/{id}")
    public String getProviderdata(@PathVariable(value = "id") long id, Model model)
    {
        Optional<Material> material = materialRepository.findById(id);
        ArrayList<Material> materialRes = new ArrayList<>();
        material.ifPresent(materialRes::add);
        model.addAttribute("material", materialRes);
        if(!materialRepository.existsById(id)){
            return "redirect:/materials";
        }
        return "MaterialPages/MaterialList";
    }
    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'STOCKER')")
    @PostMapping("/materials/remove/{id}")
    public String deleteProviderdata(@PathVariable(value = "id") long id,Model model)
    {
        Material stock = materialRepository.findById(id).orElseThrow();
        materialRepository.delete(stock);
        return "redirect:/materials";
    }
    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'STOCKER')")
    @GetMapping("/materials/edit/{id}")
    public String editProviderPage(@PathVariable(value = "id") long id, Model model)
    {

//        Optional<Product> product = productRepository.findById(id);
//        ArrayList<Product> productRes = new ArrayList<>();
//        product.ifPresent(productRes::add);
//        model.addAttribute("product", productRes);
        Material res = materialRepository.findById(id).orElseThrow();
        model.addAttribute("material",res);
        if(!materialRepository.existsById(id)){
            return "redirect:/materials";
        }
        return "MaterialPages/MaterialEdit";
    }
    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'STOCKER')")
    @PostMapping("/materials/edit/{id}")
    public String editProductdata(@PathVariable(value = "id")  long id, @Valid Material stock ,   BindingResult bindingResult)
    {
//        Product product = productRepository.findById(id).orElseThrow();
//        product.setCountry(country);
//        product.setPrice(price);
//        product.setValue(value);
//        product.setNameproduct(nameproduct);
//        product.setDescription(description);
//        product.setCreationdate(creationdate);
        if(bindingResult.hasErrors()) return "MaterialPages/MaterialEdit";
        materialRepository.save(stock);
        return "redirect:/materials";
    }

}
