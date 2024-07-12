package com.cogent.main.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Builder
public class Address
{
	 private String city; 
	 private String street;
	 private int number;
	 private String zipcode;
	 
	 public String ToString()
	 {
		 return String.valueOf(number) + " " + street + " " + city + ", " + zipcode;
	 }
}
