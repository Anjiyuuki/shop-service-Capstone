package com.cogent.main;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer>
{

	Optional<List<OrderEntity>> findByUserId(int id);

}
