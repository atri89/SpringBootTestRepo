package validator;

import org.springbootweb.testproject.entity.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {

	private static final int MINIMUM_PASSWORD_LENGTH = 6;

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		org.springbootweb.testproject.entity.User user = (User) o;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required");

		if (user.getPassword() != null && user.getPassword().trim().length() < MINIMUM_PASSWORD_LENGTH) {
			errors.rejectValue("password", "field.min.length");
		}

	}

}
