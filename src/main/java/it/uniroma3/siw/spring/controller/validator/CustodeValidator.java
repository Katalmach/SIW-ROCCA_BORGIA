package it.uniroma3.siw.spring.controller.validator;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Custode;
import it.uniroma3.siw.spring.service.CustodeService;



@Component
public class CustodeValidator implements Validator {
	
	@Autowired
	private CustodeService custodeService;
	
    private static final Logger logger = LoggerFactory.getLogger(CustodeValidator.class);

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataDiNascita", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "luogoDiNascita", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numeroDiTelefono", "required");
		
		if (!errors.hasErrors()) {
			logger.debug("confermato: valori non nulli");
			if (this.custodeService.alreadyExists((Custode)o)) {
				logger.debug("e' un duplicato");
				errors.reject("custode.duplicato");
			}
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Custode.class.equals(aClass);
	}
}
