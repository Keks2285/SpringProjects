package com.example.BarberShop.Controllers;

import com.example.BarberShop.Models.Employer;
import com.example.BarberShop.Models.Service;
import com.example.BarberShop.Models.SickLeave;
import com.example.BarberShop.Repo.EmployerRepository;
import com.example.BarberShop.Repo.ServiceRepository;
import com.example.BarberShop.Repo.SickLeaveRepository;
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
public class SickLeaveController {

    @Autowired
    private SickLeaveRepository sickLeaveRepository;

    @Autowired
    private EmployerRepository employerRepository;
    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'KADR')")
        @GetMapping("/sickleaves")
    public String MainPageGet(Model model)
    {
        SickLeave sickLeave = new SickLeave();
        Iterable<SickLeave> sickLeaves = sickLeaveRepository.findAll();
        Iterable<Employer> employers = employerRepository.findAll();
        model.addAttribute("employers",employers);
        model.addAttribute("sickLeaves",sickLeaves);
        model.addAttribute("sickLeave",sickLeave);
        return "SickLeavesPages/SickLeavesList";
    }
    @PreAuthorize("hasAnyAuthority( 'ADMIN', 'KADR')")
    @PostMapping("/sicklaleave/add")
    public String sickadd(@Valid SickLeave sickLeave, BindingResult bindingResult,
                          @RequestParam long employerid, Model model)
    {
        if(bindingResult.hasErrors()){ return "redirect:/vacations";}
        Employer employer = employerRepository.findById(employerid).orElseThrow();
        sickLeave.setEmployer(employer);
        sickLeaveRepository.save(sickLeave);
        return "redirect:/sickleaves";
    }
    @PostMapping("/sickleave/remove/{id}")
    public String sickadd(
                          @PathVariable(value = "id") long id, Model model)
    {
        SickLeave sickLeave = sickLeaveRepository.findById(id).orElseThrow();
        sickLeaveRepository.delete(sickLeave);
        return "redirect:/sickleaves";
    }


}
