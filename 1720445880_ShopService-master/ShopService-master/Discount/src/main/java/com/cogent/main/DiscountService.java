package com.cogent.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountService
{
	@Autowired
	private DiscountRepository discountRepository;
	
	public int findDiscount(String code)
	{
		return discountRepository.findByCode(code).get().getSaving();
	}

	public DiscountEntity addDiscount(DiscountEntity discountEntity)
	{
		return discountRepository.save(discountEntity);
	}

	public void delete(int id)
	{
		discountRepository.deleteById(id);
	}
	
	
}
