package it.uniroma3.siw.spring.controller;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.controller.validator.CustodeValidator;
import it.uniroma3.siw.spring.model.Custode;
import it.uniroma3.siw.spring.service.CustodeService;



@Controller
public class CustodeController {
	
	@Autowired
	private CustodeService custodeService;
	
    @Autowired
    private CustodeValidator custodeValidator;
        
    @RequestMapping(value="/admin/custode", method = RequestMethod.GET)
    public String addCustode(Model model) {
    	model.addAttribute("custode", new Custode());
        return "custodeForm";
    }

    @RequestMapping(value = "/custode/{id}", method = RequestMethod.GET)
    public String getCustode(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("custode", this.custodeService.custodePerId(id));
    	return "custode";
    }

    @RequestMapping(value = "/custode", method = RequestMethod.GET)
    public String getCustodi(Model model) {
    	model.addAttribute("custodi", this.custodeService.tutti());
    	return "custodi";
    }

    @RequestMapping(value = "/eliminaCustode/{id}", method = RequestMethod.GET)
    public String eliminaCustode(@PathVariable("id")Long id, Model model) {
    	this.custodeService.elimina(this.custodeService.custodePerId(id));
    	model.addAttribute("custodi", this.custodeService.tutti());
    	return "custodi";
    }

    
    
    
    @RequestMapping(value = "/admin/custode", method = RequestMethod.POST)
    public String addCustode(@ModelAttribute("custode") Custode custode, 
    									Model model, BindingResult bindingResult) {
    	this.custodeValidator.validate(custode, bindingResult);
        if (!bindingResult.hasErrors()) {
        	this.custodeService.inserisci(custode);
            model.addAttribute("custodi", this.custodeService.tutti());
            return "/admin/home";
        }
        return "custodiForm";
    }
}
