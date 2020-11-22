package com.example.demo.validator;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ListPositiveValidator implements ConstraintValidator<ListPositiveConstraint, List<Integer>> {

	@Override
	public void initialize(ListPositiveConstraint number) {
	}

	@Override
	public boolean isValid(List<Integer> value, ConstraintValidatorContext context) {
		for (int i=0 ;i < value.size();i++) {
			if (value.get(i) <= 0) {
				return false;
			}
		}
		return true;
	}

}