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

import it.uniroma3.siw.spring.controller.validator.CampoValidator;
import it.uniroma3.siw.spring.model.Campo;
import it.uniroma3.siw.spring.service.CampoService;

@Controller
public class CampoController {
	
	@Autowired
	private CampoService campoService;
	
    @Autowired
    private CampoValidator campoValidator;
        
    @RequestMapping(value="/admin/campo", method = RequestMethod.GET)
    public String addCampo(Model model) {
    	model.addAttribute("campo", new Campo());
        return "campoForm";
    }

    @RequestMapping(value = "/campo/{id}", method = RequestMethod.GET)
    public String getCampo(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("campo", this.campoService.campoPerId(id));
    	return "campo";
    }

    @RequestMapping(value = "/campo", method = RequestMethod.GET)
    public String getCampi(Model model) {
    		model.addAttribute("campi", this.campoService.tutti());
    		return "campi";
    }
    
    @RequestMapping(value = "/admin/campo", method = RequestMethod.POST)
    public String addCampo(@ModelAttribute("campo") Campo campo, 
    									Model model, BindingResult bindingResult) {
    	this.campoValidator.validate(campo, bindingResult);
        if (!bindingResult.hasErrors()) {
        	this.campoService.inserisci(campo);
            model.addAttribute("campi", this.campoService.tutti());
            return "campi";
        }
        return "campoForm";
    }
}
