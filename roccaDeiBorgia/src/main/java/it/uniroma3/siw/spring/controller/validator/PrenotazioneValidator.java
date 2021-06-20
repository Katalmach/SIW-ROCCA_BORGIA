package it.uniroma3.siw.spring.controller.validator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Prenotazione;
import it.uniroma3.siw.spring.service.PrenotazioneService;

@Component
public class PrenotazioneValidator implements Validator {

	@Autowired
	private PrenotazioneService prenotazioneService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Prenotazione.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"giorno" ,"required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"ora" , "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"campo" , "required");
		
		
		if(!errors.hasErrors()) {
			if(this.prenotazioneService.alreadyExists((Prenotazione)o)) {
				errors.reject("prenotazione.duplicato");
			}
		}
	}

}
