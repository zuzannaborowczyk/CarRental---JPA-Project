package com.capgemini.types;

import java.util.Date;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder

public class CustomerTO {

	private Long id;

	private String firstName;

	private String lastName;

	private AddressTO address;

	private Date customerBirthDate;

	private String email;

	
}
