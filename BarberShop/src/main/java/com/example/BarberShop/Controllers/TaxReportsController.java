package com.example.BarberShop.Controllers;

import com.example.BarberShop.Models.Employer;
import com.example.BarberShop.Models.Material;
import com.example.BarberShop.Models.TaxReport;
import com.example.BarberShop.Repo.EmployerRepository;
import com.example.BarberShop.Repo.MaterialRepository;
import com.example.BarberShop.Repo.TaxReportRepository;
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

@Controller
public class TaxReportsController {
    @Autowired
    private TaxReportRepository taxReportRepository;
    @Autowired
    private EmployerRepository employerRepository;
    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'BUHGALTER')")
    @GetMapping("/taxreports")
    public String MainPageGet(Model model)
    {
        TaxReport taxReport = new TaxReport();
        Iterable<TaxReport> taxReports = taxReportRepository.findAll();
        Iterable<Employer> employers = employerRepository.findAll();
        model.addAttribute("taxReport",taxReport);
        model.addAttribute("employers",employers);
        model.addAttribute("taxReports",taxReports);
        return "TaxReportsPages/TaxReportsList";
    }
    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'BUHGALTER')")
    @PostMapping("/taxreports/add")
    public String createreport(@Valid TaxReport taxReport, BindingResult bindingResult,
                               @RequestParam long emplyerid,Model model)
    {
        if(bindingResult.hasErrors()){

            Iterable<Employer> employers = employerRepository.findAll();

            Iterable<TaxReport> taxReports = taxReportRepository.findAll();
            model.addAttribute("taxReports",taxReports);
            model.addAttribute("employers",employers);
            return "TaxReportsPages/TaxReportsList";
        }
        Employer employer = employerRepository.findById(emplyerid).orElseThrow();
        Iterable<TaxReport> taxReports = taxReportRepository.findAll();
        model.addAttribute("taxReport",taxReport);
        model.addAttribute("taxReports",taxReports);
        taxReport.setTaxvalue(taxReport.getValuesells()*0.13);
        taxReport.setEmployer(employer);
        taxReportRepository.save(taxReport);
        return "redirect:/taxreports";
    }
    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'BUHGALTER')")
    @PostMapping("/taxreports/remove/{id}")
    public String deletereport(@PathVariable(value = "id") long id, Model model)
    {

        TaxReport taxReport = taxReportRepository.findById(id).orElseThrow();
        taxReportRepository.delete(taxReport);
        return "redirect:/taxreports";
    }

    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'BUHGALTER')")
    @GetMapping("/taxreports/{id}")
    public String editProviderPage(@PathVariable(value = "id") long id, Model model)
    {

//        Optional<Product> product = productRepository.findById(id);
//        ArrayList<Product> productRes = new ArrayList<>();
//        product.ifPresent(productRes::add);
//        model.addAttribute("product", productRes);
        Iterable<Employer> employers = employerRepository.findAll();
        model.addAttribute("employers",employers);
        TaxReport res = taxReportRepository.findById(id).orElseThrow();
        model.addAttribute("taxReport",res);
        if(!taxReportRepository.existsById(id)){
            return "redirect:/taxreports";
        }
        return "TaxReportsPages/TaxReportEdit";
    }
    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'BUHGALTER')")
    @PostMapping("/taxreports/{id}")
    public String editProductdata(@PathVariable(value = "id")  long id,
                                  @RequestParam long employerid,
                                  @Valid TaxReport taxReport ,  BindingResult bindingResult, Model model)
    {
//        Product product = productRepository.findById(id).orElseThrow();
//        product.setCountry(country);
//        product.setPrice(price);
//        product.setValue(value);
//        product.setNameproduct(nameproduct);
//        product.setDescription(description);
//        product.setCreationdate(creationdate);
        if(bindingResult.hasErrors()) {
            Iterable<Employer> employers = employerRepository.findAll();
            model.addAttribute("employers", employers);
            return "TaxReportsPages/TaxReportEdit";
        }
        Employer employer = employerRepository.findById(employerid).orElseThrow();
        taxReport.setEmployer(employer);
        taxReport.setTaxvalue(taxReport.getValuesells()*0.13);
        taxReportRepository.save(taxReport);
        return "redirect:/taxreports";
    }

}
