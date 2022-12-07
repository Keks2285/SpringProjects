package com.example.BarberShop.Controllers;

import com.example.BarberShop.Models.*;
import com.example.BarberShop.Models.Record;
import com.example.BarberShop.Repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

@Controller
public class ServiceController {
   @Autowired
   private ServiceRepository serviceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployerRepository employerRepository;
    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private IncomeRepository incomeRepository;
    @PreAuthorize("hasAnyAuthority( 'ADMIN')")
    @GetMapping("/services")
    public String MainPageGet(Model model)
    {
        Service service = new Service();
        Iterable<Service> services = serviceRepository.findAll();
        model.addAttribute("services",services);
        model.addAttribute("service",service);
        return "ServicePages/ServiceList";
    }
    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'CLIENT')")
    @GetMapping("/servicesClient")
    public String ServicePageGet(Model model)
    {
        Iterable<Service> services = serviceRepository.findAll();
        model.addAttribute("services",services);
        return "ServicePages/ServiceClientList";
    }


    @PreAuthorize("hasAnyAuthority( 'ADMIN')")
    @PostMapping("/services/add")
    public String proiderAdd(@Valid Service service,BindingResult bindingResult, @RequestParam String nameservice ,
                              Model model)
    {
       // String a = supply.getDatesupply().toString();
        model.addAttribute("services", serviceRepository.findAll());
        Service service_db=serviceRepository.findByNameservice(nameservice);
        if (service_db!=null) bindingResult.addError(new ObjectError("nameservice", "Такая услуга уже существует"));
        if(bindingResult.hasErrors()){return "ServicePages/ServiceList";}


        // Product product = new Product(nameproduct,price,value, country, description,creationdate);
        serviceRepository.save(service);
        return "redirect:/services";
    }

    @PreAuthorize("hasAnyAuthority( 'ADMIN')")
    //@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'EMPLOYER')")
    @GetMapping("/services/{id}")
    public String getProviderdata(@PathVariable(value = "id") long id, Model model)
    {
        Service service = serviceRepository.findById(id).orElseThrow();

        model.addAttribute("service", service);
        //model.addAttribute("materials", materialRepository.findAll());
        if(!serviceRepository.existsById(id)){
            return "redirect:/services";
        }
        return "ServicePages/ServiceEdit";
    }
    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'CLIENT')")
    @GetMapping("/services/recordAdd/{id}")
    public String getServicesdata(@PathVariable(value = "id") long id, Model model)
    {
        Service service = serviceRepository.findById(id).orElseThrow();

        Iterable<Employer> employers = employerRepository.findAll();
        model.addAttribute("employers",employers);
        model.addAttribute("service",service);
        //model.addAttribute("materials", materialRepository.findAll());
        if(!serviceRepository.existsById(id)){
            return "redirect:/servicesClient";
        }
        return "ServicePages/CreateRecord";
    }
    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'CLIENT')")
    @PostMapping("/services/recordAdd/{id}")
    public String postRecordAdd(@PathVariable(value = "id")  long id,
                                @RequestParam long employerid,
                                @RequestParam String daterecord,Model model)
    {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Service service = serviceRepository.findById(id).orElseThrow();
        Employer master = employerRepository.findById(employerid).orElseThrow();
      //  auth.getAuthorities().
        User user = userRepository.findByEmail(auth.getName());
        Client client =user.getClient();

        Record record = new Record();
        record.setEmployer(master);
        record.setClient(client);
        record.setService(service);
        record.setDaterecord(Date.valueOf(daterecord));
        record = recordRepository.save(record);
        Income income = new Income(record);
        incomeRepository.save(income);
        //model.addAttribute("materials", materialRepository.findAll());

        return "redirect:/servicesClient";
    }

    @PreAuthorize("hasAnyAuthority( 'ADMIN')")
    @PostMapping("/services/remove/{id}")
    public String deleteProviderdata(@PathVariable(value = "id") long id,Model model)
    {
        Service service = serviceRepository.findById(id).orElseThrow();
        serviceRepository.delete(service);
        return "redirect:/services";
    }





    @PreAuthorize("hasAnyAuthority( 'ADMIN')")
    @GetMapping("/services/edit/{id}")
    public String editProviderPage(@PathVariable(value = "id") long id, Model model)
    {

//        Optional<Product> product = productRepository.findById(id);
//        ArrayList<Product> productRes = new ArrayList<>();
//        product.ifPresent(productRes::add);
//        model.addAttribute("product", productRes);
        Service res = serviceRepository.findById(id).orElseThrow();
        model.addAttribute("service",res);
        if(!serviceRepository.existsById(id)){
            return "redirect:/services";
        }
        return "ServicePages/ServiceEdit";
    }
    @PreAuthorize("hasAnyAuthority( 'ADMIN')")
    @PostMapping("/services/edit/{id}")
    public String editProductdata(@PathVariable(value = "id")  long id,
                                  @Valid Service service ,   BindingResult bindingResult)
    {
//        Product product = productRepository.findById(id).orElseThrow();
//        product.setCountry(country);
//        product.setPrice(price);
//        product.setValue(value);
//        product.setNameproduct(nameproduct);
//        product.setDescription(description);
//        product.setCreationdate(creationdate);
        if(bindingResult.hasErrors()) return "ServicePages/ServiceEdit";
        serviceRepository.save(service);
        return "redirect:/services";
    }

}
