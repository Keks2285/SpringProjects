package com.example.BarberShop.Controllers;

import com.example.BarberShop.Models.Provider;
import com.example.BarberShop.Repo.ProviderRepository;
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
public class ProviderController {
    @Autowired
    private ProviderRepository providerRepository;
    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'STOCKER')")
    @GetMapping("/providers")
    public String MainPageGet(Model model)
    {
        Provider provider = new Provider();
        Iterable<Provider> providers = providerRepository.findAll();
        model.addAttribute("providers",providers);
        model.addAttribute("provider",provider);
        return "ProvidersPages/ProviderList";
    }
    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'STOCKER')")
    @PostMapping("/providers/filter")
    public String providersSearch(@RequestParam String nameprovider, Model model)
    {
        Provider provider = new Provider();
        List<Provider> providers = providerRepository.findByNameproviderContains(nameprovider);
        model.addAttribute("providers", providers);
        model.addAttribute("provider",provider);
        return "ProvidersPages/ProviderList";
    }
    @PostMapping("/providers/add")
    public String proiderAdd(@Valid Provider provider,
                             BindingResult bindingResult, Model model)
    {
        model.addAttribute("providers", providerRepository.findAll());
        if(providerRepository.findByInn(provider.getInn())!=null) //не выводит сообщение
            bindingResult.addError(new ObjectError("inn",  "Уже существует поставщик с таким ИНН"));

        if(providerRepository.findByInn(provider.getInn())!=null) //не выводит сообщение
            bindingResult.addError(new ObjectError("inn",  "Уже существует поставщик с таким инн"));

        if(providerRepository.findByNameprovider(provider.getNameprovider())!=null) //не выводит сообщение
            bindingResult.addError(new ObjectError("nameprovider",  "Уже существует поставщик с таким Названием"));

        if(bindingResult.hasErrors()){return "ProvidersPages/ProviderList";}
        // Product product = new Product(nameproduct,price,value, country, description,creationdate);
        providerRepository.save(provider);
        return "redirect:/providers";
    }


    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'STOCKER')")
    //@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'EMPLOYER')")
    @GetMapping("/provider/{id}")
    public String getProviderdata(@PathVariable(value = "id") long id, Model model)
    {
        Optional<Provider> provider = providerRepository.findById(id);
        ArrayList<Provider> providerRes = new ArrayList<>();
        provider.ifPresent(providerRes::add);
        model.addAttribute("provider", providerRes);
        if(!providerRepository.existsById(id)){
            return "redirect:/providers";
        }
        return "ProvidersPages/ProviderDetails";
    }
    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'STOCKER')")
    @PostMapping("/provider/remove/{id}")
    public String deleteProviderdata(@PathVariable(value = "id") long id,Model model)
    {
        Provider provider = providerRepository.findById(id).orElseThrow();
        providerRepository.delete(provider);
        return "redirect:/providers";
    }
    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'STOCKER')")
    @GetMapping("/provider/edit/{id}")
    public String editProviderPage(@PathVariable(value = "id") long id, Model model)
    {

//        Optional<Product> product = productRepository.findById(id);
//        ArrayList<Product> productRes = new ArrayList<>();
//        product.ifPresent(productRes::add);
//        model.addAttribute("product", productRes);
        Provider res = providerRepository.findById(id).orElseThrow();
        model.addAttribute("provider",res);
        if(!providerRepository.existsById(id)){
            return "redirect:/providers";
        }
        return "ProvidersPages/ProviderEdit";
    }
    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'STOCKER')")
    @PostMapping("/provider/edit/{id}")
    public String editProductdata(@PathVariable(value = "id")  long id, @Valid Provider product ,   BindingResult bindingResult)
    {
//        Product product = productRepository.findById(id).orElseThrow();
//        product.setCountry(country);
//        product.setPrice(price);
//        product.setValue(value);
//        product.setNameproduct(nameproduct);
//        product.setDescription(description);
//        product.setCreationdate(creationdate);
        if(bindingResult.hasErrors()) return "ProvidersPages/ProviderEdit";
        providerRepository.save(product);
        return "redirect:/providers";
    }

}
