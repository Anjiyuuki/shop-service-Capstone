package com.cogent.main;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService
{
	@Autowired
	private ProductRepository productRepository;

	public List<ProductEntity> getAllProducts()
	{
		return productRepository.findAll();
	}

	public ProductEntity addNewProduct(ProductEntity productEntity)
	{
		return productRepository.save(productEntity);
	}

	public ProductEntity getOneProduct(int id)
	{
		return productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product does not Exist"));
	}

	public ProductEntity updateProduct(ProductEntity productEntity, int id)
	{
		productEntity.setId(id);
		return productRepository.save(productEntity);
	}

	public void deleteProduct(int id)
	{
		productRepository.deleteById(id);
	}

	public List<ProductEntity> getByCategory(String category)
	{
		return productRepository.findAllByCategory(category);
	}

	public List<String> getCategories()
	{
		return productRepository.findCategories();
	}

}
