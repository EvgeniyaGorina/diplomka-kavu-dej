package cz.takeaway.app.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

	private static final String PHONE = "^[+.-]?\\d{4,15}$"; 
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value.matches(PHONE);
	}

}
