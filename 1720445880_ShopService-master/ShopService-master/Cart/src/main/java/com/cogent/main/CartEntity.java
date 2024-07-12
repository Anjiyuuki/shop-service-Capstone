package com.cogent.main;

import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class CartEntity
{
	//Since each user has exactly one unique cart, the userid is used as the cartid
	@Id
	private int userId;
	@OneToMany(mappedBy = "cartEntity")
	@Cascade(CascadeType.ALL)
	private List<CartItem> cartItems;
}
