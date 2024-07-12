package com.cogent.main;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer>
{

	List<ProductEntity> findAllByCategory(String category);

	@Query("SELECT DISTINCT p.category FROM ProductEntity p")
	List<String> findCategories();

}
