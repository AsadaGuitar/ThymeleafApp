package com.example.web;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
//@Data
public class CustomerForm {

	@NotNull
	@Size(min = 1, max = 127)
	private String firstName;

	@NotNull
	@Size(min = 1, max = 127)
	private String lastName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
