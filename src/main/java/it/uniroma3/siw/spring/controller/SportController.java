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

import it.uniroma3.siw.spring.controller.validator.SportValidator;
import it.uniroma3.siw.spring.model.Sport;
import it.uniroma3.siw.spring.service.SportService;

@Controller
public class SportController {
	
	@Autowired
	private SportService sportService;
	
    @Autowired
    private SportValidator sportValidator;
        
    @RequestMapping(value="/admin/sport", method = RequestMethod.GET)
    public String addSport(Model model) {
    	model.addAttribute("sport", new Sport());
        return "sportForm";
    }

    @RequestMapping(value = "/sport/{id}", method = RequestMethod.GET)
    public String getSport(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("sport", this.sportService.sportPerId(id));
    	return "sport";
    }

    @RequestMapping(value = "/sport", method = RequestMethod.GET)
    public String getSports(Model model) {
    		model.addAttribute("sports", this.sportService.tutti());
    		return "sports";
    }
    
    @RequestMapping(value = "/admin/sport", method = RequestMethod.POST)
    public String addSport(@ModelAttribute("sport") Sport sport, 
    									Model model, BindingResult bindingResult) {
    	this.sportValidator.validate(sport, bindingResult);
        if (!bindingResult.hasErrors()) {
        	this.sportService.inserisci(sport);
            model.addAttribute("sports", this.sportService.tutti());
            return "sports";
        }
        return "sportForm";
    }
}
