package com.example.BarberShop.Controllers;

import com.example.BarberShop.Models.Consumption;
import com.example.BarberShop.Models.Material;
import com.example.BarberShop.Models.Provider;
import com.example.BarberShop.Models.Supply;
import com.example.BarberShop.Repo.ConsumptionRepository;
import com.example.BarberShop.Repo.MaterialRepository;
import com.example.BarberShop.Repo.ProviderRepository;
import com.example.BarberShop.Repo.SupplyRepository;
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
public class SupplyController {
    @Autowired
    private SupplyRepository supplyRepository;
    @Autowired
    private ConsumptionRepository consumptionRepository;

    @Autowired
    private ProviderRepository providerRepository;
    @Autowired
    private MaterialRepository materialRepository;
    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'STOCKER')")
    @GetMapping("/supplies")
    public String MainPageGet(Model model)
    {
        Supply supply = new Supply();
        Iterable<Supply> supplies = supplyRepository.findAll();
        Iterable<Provider> providers = providerRepository.findAll();
        model.addAttribute("supplies",supplies);
        model.addAttribute("supply",supply);
        model.addAttribute("providers",providers);
        return "SupplyPages/SupplyList";
    }
    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'STOCKER')")
    @PostMapping("/supplies/add")
    public String proiderAdd(@Valid Supply supply, BindingResult bindingResult,
                             @RequestParam String nameprovider,
                             Model model)
    {
       // String a = supply.getDatesupply().toString();
        model.addAttribute("supplies", supplyRepository.findAll());
        if(bindingResult.hasErrors()){

            Iterable<Provider> providers = providerRepository.findAll();

            model.addAttribute("providers",providers);
            return "SupplyPages/SupplyList";
        }
        Provider provider_supply =providerRepository.findByNameprovider(nameprovider);
        supply.setProvider(provider_supply);

        Consumption consumption = new Consumption(supply.getDatesupply(), supply.getPrice());
        consumption.setSupply(supply);
        consumptionRepository.save(consumption);
        // Product product = new Product(nameproduct,price,value, country, description,creationdate);

        supplyRepository.save(supply);
        return "redirect:/supplies";
    }

    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'STOCKER')")
    //@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'EMPLOYER')")
    @GetMapping("/supplies/{id}")
    public String getProviderdata(@PathVariable(value = "id") long id, Model model)
    {
        Optional<Supply> supply = supplyRepository.findById(id) ;
        ArrayList<Supply> supplyRes = new ArrayList<>();
        supply.ifPresent(supplyRes::add);

        model.addAttribute("supply", supplyRes);
        //model.addAttribute("materials", materialRepository.findAll());
        if(!supplyRepository.existsById(id)){
            return "redirect:/supplies";
        }
        return "SupplyPages/SupplyDetails";
    }
    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'STOCKER')")
    @PostMapping("/supplies/remove/{id}")
    public String deleteProviderdata(@PathVariable(value = "id") long id,Model model)
    {
        Supply supply = supplyRepository.findById(id).orElseThrow();
        supplyRepository.delete(supply);
        return "redirect:/supplies";
    }
    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'STOCKER')")
    @PostMapping("/supplies/material/remove/{id}/{idmaterial}")
    public String deletematerialdata(@PathVariable(value = "id") long id,
                                     @PathVariable(value = "idmaterial") long idmaterial,
                                     Model model)
    {
        Supply supply = supplyRepository.findById(id).orElseThrow();
        Material material = materialRepository.findById(idmaterial).orElseThrow();
        supply.getMaterials().remove(material);
        supplyRepository.save(supply);
        return "redirect:/supplies";
    }
    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'STOCKER')")
    @PostMapping("/supplies/material/add/{id}")
    public String creaatematerialdata(@PathVariable(value = "id") long id,
                                     @RequestParam(value = "name") String name,
                                     Model model)
    {
        Supply supply = supplyRepository.findById(id).orElseThrow();
        Material material = materialRepository.findByName(name);

        supply.getMaterials().add(material);
        supplyRepository.save(supply);
        return "redirect:/supplies";
    }


    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'STOCKER')")
    @GetMapping("/supplies/edit/{id}")
    public String editProviderPage(@PathVariable(value = "id") long id, Model model)
    {

//        Optional<Product> product = productRepository.findById(id);
//        ArrayList<Product> productRes = new ArrayList<>();
//        product.ifPresent(productRes::add);
//        model.addAttribute("product", productRes);
        model.addAttribute("providers", providerRepository.findAll());
        model.addAttribute("materials", materialRepository.findAll());
        Supply res = supplyRepository.findById(id).orElseThrow();
        model.addAttribute("supply",res);
        if(!supplyRepository.existsById(id)){
            return "redirect:/supplies";
        }
        return "SupplyPages/SupplyEdit";
    }
    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'STOCKER')")
    @PostMapping("/supplies/edit/{id}")
    public String editProductdata(@PathVariable(value = "id")  long id,
                                  @RequestParam(value = "nameprovider")  String nameprovider,
                                  @Valid Supply supply ,   BindingResult bindingResult)
    {
//        Product product = productRepository.findById(id).orElseThrow();
//        product.setCountry(country);
//        product.setPrice(price);
//        product.setValue(value);
//        product.setNameproduct(nameproduct);
//        product.setDescription(description);
//        product.setCreationdate(creationdate);
        if(bindingResult.hasErrors()) return "SupplyPages/SupplyEdit";
        supply.setProvider(providerRepository.findByNameprovider(nameprovider));
        supplyRepository.save(supply);
        return "redirect:/supplies";
    }

}
