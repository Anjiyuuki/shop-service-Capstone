package com.cogent.main.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDAO
{
	int id;
	Name name;
	String email;
	Address address;
	String phoneNumber;
	String role;
}
