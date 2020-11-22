package com.example.demo.model;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.example.demo.validator.ListPositiveConstraint;

public class ParamModel {
	
	
	@ListPositiveConstraint(message = ">0")
	public List<Integer> setlist;
	
	@Valid
	@NotNull
	@Positive(message = ">0")
	public Integer target;
	
}
