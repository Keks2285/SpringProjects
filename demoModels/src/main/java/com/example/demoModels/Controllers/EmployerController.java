package com.example.demoModels.Controllers;

import com.example.demoModels.Models.Employe;
import com.example.demoModels.Repo.EmployeRepository;
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
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class EmployerController {

    @Autowired
    private EmployeRepository employeRepository;


    @PostMapping("/employe/add")
    public String employeAdd(@Valid Employe employe,
                             BindingResult bindingResult)
    {

        if(bindingResult.hasErrors()){return "EmployeCreate";}
        // Product product = new Product(nameproduct,price,value, country, description,creationdate);
        employeRepository.save(employe);
        return "redirect:/";
        // if (isalive==null) isalive=false;
        //    Employe employe = new Employe(firstname,lastname,middlename, age, oklad,isalive);
        //employeRepository.save(employe);
        //return "redirect:/";
    }


    @GetMapping("/employe/add")
    public String employeAddView(Employe employe)
    {
        return "EmployeCreate";
    }



    @GetMapping("/employe/filter")
    public String employeFilter(Model model)
    {
        return "FilterEmploye";
    }


    @PostMapping("/employe/filterAll/result")
    public String employeResultAll(@RequestParam String firstname, Model model)
    {
        List<Employe> result = employeRepository.findByFirstname(firstname);
        model.addAttribute("result", result);
        return "FilterEmploye";
    }



    @GetMapping("/employe/{id}")
    public String getEmployedata(@PathVariable(value = "id") long id, Model model)
    {
        Optional<Employe> employe = employeRepository.findById(id);
        ArrayList<Employe> employeRes = new ArrayList<>();
        employe.ifPresent(employeRes::add);
        model.addAttribute("employe", employeRes);
        if(!employeRepository.existsById(id)){
            return "redirect:/main";
        }
        return "EmployeDetails";
    }

    @PostMapping("/employe/remove/{id}")
    public String deleteEmployedata(@PathVariable(value = "id") long id,Model model)
    {
        Employe employe = employeRepository.findById(id).orElseThrow();
        employeRepository.delete(employe);
        return "redirect:/main";
    }

    @GetMapping("/employe/edit/{id}")
    public String editEmployedataPage(@PathVariable(value = "id") long id,Model model)
    {
        Employe res = employeRepository.findById(id).orElseThrow();
        model.addAttribute("employe",res);
        if(!employeRepository.existsById(id)){
            return "redirect:/main";
        }
        return "EmployeEdit";

//        Optional<Employe> employe = employeRepository.findById(id);
//        ArrayList<Employe> employeRes = new ArrayList<>();
//        employe.ifPresent(employeRes::add);
//        model.addAttribute("employe", employeRes);
//        if(!employeRepository.existsById(id)){
//            return "redirect:/";
//        }
//        return "EmployeEdit";
    }
    @PostMapping("/employe/edit/{id}")
    public String editEmployedata(@PathVariable(value = "id")  long id, @Valid Employe employe ,
                                  BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()) return "EmployeEdit";
        employeRepository.save(employe);
        return "redirect:/main";
    }

    @PostMapping("/employe/filterContains/result")
    public String employeResultContains(@RequestParam String firstname, Model model)
    {
        List<Employe> result = employeRepository.findByFirstnameContains(firstname);
        model.addAttribute("result", result);
        return "FilterEmploye";
    }
}
