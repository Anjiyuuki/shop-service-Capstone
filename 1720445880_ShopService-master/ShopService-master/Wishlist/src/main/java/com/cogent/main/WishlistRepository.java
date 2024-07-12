package com.cogent.main;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends JpaRepository<WishlistEntity, Integer>
{

	Optional<WishlistEntity> findByUserId(int id);

}
