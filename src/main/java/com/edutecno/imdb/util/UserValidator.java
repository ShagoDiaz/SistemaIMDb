package com.edutecno.imdb.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.edutecno.imdb.model.User;
import com.edutecno.imdb.service.UserService;

@Component
public class UserValidator implements Validator {

	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		User user = (User) object;
		if (!user.getPasswordConfirmation().equals(user.getPassword())) {
			errors.rejectValue("passwordConfirmation", "Match");
		}
		User userCheck = userService.findByEmail(user.getEmail());

		if (userCheck != null) {
			errors.rejectValue("email", "found");
		}

	}
}
