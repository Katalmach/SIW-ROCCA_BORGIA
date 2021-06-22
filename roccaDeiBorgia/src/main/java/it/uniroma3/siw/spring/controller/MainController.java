package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.service.CustodeService;

@Controller
public class MainController {
	
	@Autowired
	private CustodeService custodeService;

	@RequestMapping(value = {"/", "index"}, method = RequestMethod.GET)
	public String index(Model model) {
			return "index";
	}
	
    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String getPaginaAbout(Model model) {
    		model.addAttribute("custodi", this.custodeService.tutti());
    		return "about";
    }
}
