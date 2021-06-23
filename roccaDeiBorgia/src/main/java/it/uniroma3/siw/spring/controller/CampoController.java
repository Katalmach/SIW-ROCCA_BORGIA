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
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.spring.controller.validator.CampoValidator;
import it.uniroma3.siw.spring.model.Campo;
import it.uniroma3.siw.spring.service.CampoService;
import it.uniroma3.siw.spring.service.CustodeService;
import it.uniroma3.siw.spring.service.SportService;

@Controller
public class CampoController {
	
	@Autowired
	private CampoService campoService;
	
    @Autowired
    private CampoValidator campoValidator;

    @Autowired
	private SportService sportService;

    @Autowired
	private CustodeService custodeService;
        
    @RequestMapping(value="/admin/campo", method = RequestMethod.GET)
    public String addCampo(Model model) {
    	model.addAttribute("campo", new Campo());
    	model.addAttribute("sports", this.sportService.tutti());
    	model.addAttribute("custodi", this.custodeService.tutti());
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
    
    @RequestMapping(value = "/eliminaCampo/{id}", method = RequestMethod.GET)
    public String eliminaCampo(@PathVariable("id")Long id, Model model) {
    	this.campoService.elimina(this.campoService.campoPerId(id));
    	model.addAttribute("campi", this.campoService.tutti());
    	return "campi";
    }
    
    @RequestMapping(value = "/admin/campo", method = RequestMethod.POST)
    public String addCampo(@ModelAttribute("campo") Campo campo, 
    									Model model, @RequestParam Long sportSelezionato,
    									@RequestParam Long custodeSelezionato, 
    									BindingResult bindingResult) {
    	campo.setSport(this.sportService.sportPerId(sportSelezionato));
    	campo.setCustode(this.custodeService.custodePerId(custodeSelezionato));
    	this.campoValidator.validate(campo, bindingResult);
        if (!bindingResult.hasErrors()) {
        	this.campoService.inserisci(campo);
            model.addAttribute("campi", this.campoService.tutti());
            return "campi";
        }
        return "campoForm";
     
    }
}
