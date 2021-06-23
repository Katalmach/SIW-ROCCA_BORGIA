package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.spring.controller.validator.PrenotazioneValidator;
import it.uniroma3.siw.spring.model.Credentials;
import it.uniroma3.siw.spring.model.Prenotazione;
import it.uniroma3.siw.spring.service.CampoService;
import it.uniroma3.siw.spring.service.CredentialsService;
import it.uniroma3.siw.spring.service.PrenotazioneService;
import it.uniroma3.siw.spring.service.SportService;

@Controller
public class PrenotazioneController {

	@Autowired
	private PrenotazioneService prenotazioneService;

	@Autowired
	private CampoService campoService;

	@Autowired
	private PrenotazioneValidator prenotazioneValidator;

	@Autowired
	private CredentialsService credentialsService;

	@Autowired
	private SportService sportService;


	@RequestMapping(value = "/prenotazione/{id}", method = RequestMethod.GET)
	public String getPrenotazione(@PathVariable("id") Long id, Model model) {
		model.addAttribute("prenotazione", this.prenotazioneService.prenotazionePerId(id));
		return "prenotazione";
	}

	@RequestMapping(value="/guardaPrenotazioni", method=RequestMethod.GET)
	public String guardaPrenotazioni(Model model) {
		model.addAttribute("campi", this.campoService.tutti());
		return "guardaPrenotazioni";
	}
	@RequestMapping(value="/prenotazioniCampo/{id}", method=RequestMethod.GET)
	public String getPrenotazioniCampo(Model model, @PathVariable("id")Long id) {
		model.addAttribute("sports",this.sportService.tutti());
		model.addAttribute("campi",this.campoService.tutti());
		model.addAttribute("prenotazioniCampo", this.prenotazioneService.prenotazioniPerCampo(this.campoService.campoPerId(id)));
		model.addAttribute("campoSelezionato", this.campoService.campoPerId(id));
		return "prenotazioniCampo";
	}

	@RequestMapping(value = "/prenotazioniUtente", method = RequestMethod.GET)
	public String getPrenotazioniUtente(Model model) {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = this.credentialsService.getCredentials(userDetails.getUsername());
		model.addAttribute("prenotazioni", this.prenotazioneService.prenotazioniPerUtente(credentials.getUser()));
		return "prenotazioniUtente";
	}

	@RequestMapping(value = "/admin/prenotazioni", method = RequestMethod.GET)
	public String getPrenotazioniAdmin(Model model) {
		model.addAttribute("prenotazioni", this.prenotazioneService.tutti());
		return "/admin/prenotazioniAdmin";
	}

	@RequestMapping(value ="/addPrenotazione", method= RequestMethod.POST)
	public String addPrenotazione(Model model,@RequestParam Long campoSelezionato,
			@ModelAttribute("prenotazione")Prenotazione prenotazione, BindingResult prenotBindingResult) {

		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = this.credentialsService.getCredentials(userDetails.getUsername());

		prenotazione.setCampo(this.campoService.campoPerId(campoSelezionato));
		prenotazione.setUser(credentials.getUser());

		this.prenotazioneValidator.validate(prenotazione,prenotBindingResult);
		if(!prenotBindingResult.hasErrors()) {
			this.prenotazioneService.inserisci(prenotazione);
			model.addAttribute("prenotazioni", this.prenotazioneService.prenotazioniPerUtente(credentials.getUser()));
			return "prenotazioniUtente";
		}
		model.addAttribute("campi",this.campoService.tutti());
		return "prenotazioneForm";
	}

	@RequestMapping(value="/addPrenotazione", method= RequestMethod.GET)
	public String addPrenotazione(Model model) {
		model.addAttribute("prenotazione", new Prenotazione());
		model.addAttribute("campi",this.campoService.tutti());
		model.addAttribute("prenotazioni", this.prenotazioneService.tutti());
		return "prenotazioneForm";
	}

	@RequestMapping(value = "/eliminaPrenotazione/{id}", method = RequestMethod.POST)
	public String eliminaPrenotazione(@PathVariable("id") Long id, Model model) {
		this.prenotazioneService.elimina(this.prenotazioneService.prenotazionePerId(id));
		model.addAttribute("campi", this.campoService.tutti());
		model.addAttribute("prenotazioni", this.prenotazioneService.tutti());
		return "/admin/prenotazioniAdmin";
	}



	//	@RequestMapping(value="/addPrenotazione", method= RequestMethod.GET)
	//	public String addPrenotazione(Model model) {
	//		model.addAttribute("prenotazione", new Prenotazione());
	//		model.addAttribute("campi",this.campoService.tutti());
	//		model.addAttribute("prenotazioni", this.prenotazioneService.tutti());
	//		return "prenotazioneIntermediaForm";
	//	}
	//
	//
	//	@RequestMapping(value ="/addPrenotazione", method= RequestMethod.POST)
	//	public String addPrenotazione(Model model,@RequestParam Long campoSelezionato,
	//			@ModelAttribute("prenotazione")Prenotazione prenotazione, BindingResult prenotBindingResult) {
	//
	//		model.addAttribute("campoSelezionato",this.campoService.campoPerId(campoSelezionato));
	//		model.addAttribute("prenotazioniCampo",this.prenotazioneService.prenotazioniPerCampo(this.campoService.campoPerId(campoSelezionato)));
	//		prenotazione.setCampo(this.campoService.campoPerId(campoSelezionato));
	//		model.addAttribute("prenotazione", prenotazione);
	//		
	//		return "prenotazioneForm";
	//	}
	//
	//	
	//	@RequestMapping(value="/confermaPrenotazione", method=RequestMethod.POST)
	//	public String confermaPrenotazione(Model model, @ModelAttribute("prenotazione")Prenotazione prenotazione,
	//			BindingResult prenotBindingResult) {
	//
	//		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	//		Credentials credentials = this.credentialsService.getCredentials(userDetails.getUsername());
	//		
	//		prenotazione.setCampo();
	//		prenotazione.setUser(credentials.getUser());
	//		
	//		if(prenotazione.getCampo()!=null && prenotazione.getGiorno()!=null) {
	//			this.prenotazioneService.inserisci(prenotazione);	
	//			model.addAttribute("prenotazioni",this.prenotazioneService.prenotazioniPerUtente(prenotazione.getUser()));
	//			return "prenotazioniUtente";
	//		}
	//		return "prenotazioneForm";
	//	}
	
/// PROVA DUE
	
	//	@RequestMapping(value= "/addPrenotazione", method =RequestMethod.GET)
	//	public String addPrenotazione(Model model) {
	//		model.addAttribute("campi",this.campoService.tutti());
	//		model.addAttribute("prenotazioni", this.prenotazioneService.tutti());
	//		model.addAttribute("prenotazione", new Prenotazione());
	//		return "prenotazioneIntermediaForm";
	//
	//	}
	//
	//	@RequestMapping(value="/addPrenotazione", method=RequestMethod.POST)
	//	public String confermaPrenotazione(Model model,@ModelAttribute("prenotazione")Prenotazione prenotazione, 
	//			@RequestParam Long campoSelezionato) {
	//		prenotazione.setCampo(this.campoService.campoPerId(campoSelezionato));
	//		this.prenotazioneDaSalvareService.inserisci(prenotazione);
	//		model.addAttribute("idPrenotazione",prenotazione.getId());
	//		model.addAttribute("prenotazioniDelGiorno",this.prenotazioneService.prenotazioniPerCampoEGiorno(
	//				this.campoService.campoPerId(campoSelezionato),prenotazione.getGiorno()));
	//		return "confermaPrenotazioneForm";
	//		
	//		
	//	}
	//
	//	@RequestMapping(value="/confermaPrenotazione", method=RequestMethod.POST)
	//	public String confermaPrenotazioneFinal(Model model,
	//			@PathVariable("idPrenotazione")Long id,
	//			BindingResult prenotBindingResult) {
	//		Prenotazione prenotazione = this.prenotazioneDaSalvareService.prenotazionePerId(idPrenotazione);
	//		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	//		Credentials credentials = this.credentialsService.getCredentials(userDetails.getUsername());
	//
	//		prenotazione.setUser(credentials.getUser());
	//		this.prenotazioneValidator.validate(prenotazione, prenotBindingResult);
	//		if(!prenotBindingResult.hasErrors()) {
	//			this.prenotazioneService.inserisci(prenotazione);
	//			model.addAttribute("prenotazioni", this.prenotazioneService.prenotazioniPerUtente(credentials.getUser()));
	//			this.prenotazioneDaSalvareService.elimina(prenotazione);
	//			return "prenotazioniUtente";
	//		}
	//		return "confermaPrenotazioneForm";
	//	}

}
