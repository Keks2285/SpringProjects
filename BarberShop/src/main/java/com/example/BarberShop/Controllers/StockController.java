package com.example.BarberShop.Controllers;

import com.example.BarberShop.Models.Stock;
import com.example.BarberShop.Repo.ProviderRepository;
import com.example.BarberShop.Repo.StockRepository;
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
public class StockController {
    @Autowired
    private StockRepository stockRepository;
    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'STOCKER')")
    @GetMapping("/stocks")
    public String MainPageGet(Model model)
    {
        Stock stock = new Stock();
        Iterable<Stock> stocks = stockRepository.findAll();
        model.addAttribute("stocks",stocks);
        model.addAttribute("stock",stock);
        return "StockPages/StockList";
    }
    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'STOCKER')")
    @PostMapping("/stocks/filter")
    public String providersSearch(@RequestParam String address, Model model)
    {
        Stock stock = new Stock();
        List<Stock> stocks = stockRepository.findByAddressContains(address);
        model.addAttribute("stocks", stocks);
        model.addAttribute("stock",stock);
        return "StockPages/StockList";
    }
    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'STOCKER')")
    @PostMapping("/stocks/add")
    public String proiderAdd(@Valid Stock stock,
                             BindingResult bindingResult, Model model)
    {
        model.addAttribute("stocks", stockRepository.findAll());
        if(stockRepository.findByAddress(stock.getAddress())!=null) //не выводит сообщение
            bindingResult.addError(new ObjectError("address",  "Уже существует склад с таким адресом"));

        if(bindingResult.hasErrors()){return "StockPages/StockList";}
        // Product product = new Product(nameproduct,price,value, country, description,creationdate);
        stockRepository.save(stock);
        return "redirect:/stocks";
    }

    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'STOCKER')")
    //@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'EMPLOYER')")
    @GetMapping("/stock/{id}")
    public String getProviderdata(@PathVariable(value = "id") long id, Model model)
    {
        Optional<Stock> stock = stockRepository.findById(id);
        ArrayList<Stock> stockRes = new ArrayList<>();
        stock.ifPresent(stockRes::add);
        model.addAttribute("stock", stockRes);
        if(!stockRepository.existsById(id)){
            return "redirect:/stocks";
        }
        return "StockPages/StockList";
    }
    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'STOCKER')")
    @PostMapping("/stock/remove/{id}")
    public String deleteProviderdata(@PathVariable(value = "id") long id,Model model)
    {
        Stock stock = stockRepository.findById(id).orElseThrow();
        stockRepository.delete(stock);
        return "redirect:/stocks";
    }
    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'STOCKER')")
    @GetMapping("/stock/edit/{id}")
    public String editProviderPage(@PathVariable(value = "id") long id, Model model)
    {

//        Optional<Product> product = productRepository.findById(id);
//        ArrayList<Product> productRes = new ArrayList<>();
//        product.ifPresent(productRes::add);
//        model.addAttribute("product", productRes);
        Stock res = stockRepository.findById(id).orElseThrow();
        model.addAttribute("stock",res);
        if(!stockRepository.existsById(id)){
            return "redirect:/stocks";
        }
        return "StockPages/StockEdit";
    }
    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'STOCKER')")
    @PostMapping("/stock/edit/{id}")
    public String editProductdata(@PathVariable(value = "id")  long id, @Valid Stock stock ,   BindingResult bindingResult)
    {
//        Product product = productRepository.findById(id).orElseThrow();
//        product.setCountry(country);
//        product.setPrice(price);
//        product.setValue(value);
//        product.setNameproduct(nameproduct);
//        product.setDescription(description);
//        product.setCreationdate(creationdate);
        if(bindingResult.hasErrors()) return "StockPages/StockEdit";
        stockRepository.save(stock);
        return "redirect:/stocks";
    }

}
