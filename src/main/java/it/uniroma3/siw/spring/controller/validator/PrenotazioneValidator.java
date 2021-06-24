package it.uniroma3.siw.spring.controller.validator;


import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Prenotazione;
import it.uniroma3.siw.spring.service.PrenotazioneService;

@Component
public class PrenotazioneValidator implements Validator {
  
    final Integer MIN_ORA = 9;
    final Integer MAX_ORA = 22;

    
  @Autowired
  private PrenotazioneService prenotazioneService;

  @Override
  public boolean supports(Class<?> clazz) {
    return Prenotazione.class.equals(clazz);
  }

  @Override
  public void validate(Object o, Errors errors) {
        Prenotazione prenotazione = (Prenotazione) o;
        int ora = prenotazione.getOra();
        LocalDate giorno = prenotazione.getGiorno();
        
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "campo", "required");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "giorno", "required");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ora", "required");

    
    if(ora < MIN_ORA || ora > MAX_ORA) {
      errors.rejectValue("ora", "prenotazione.oraSbagliata");
    }
    if(giorno.isBefore(LocalDate.now()))
    	errors.rejectValue("giorno", "prenotazione.giornoSbagliato");
    
    if(this.prenotazioneService.alreadyExists((Prenotazione)o)) {
      errors.rejectValue("ora", "prenotazione.duplicato");
    }
  }
}