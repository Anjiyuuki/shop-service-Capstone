package com.cogent.main;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductItemWriter implements ItemWriter<ProductEntity>{

	private final ProductRepository productRepository;

	@Override
	public void write(Chunk <? extends ProductEntity> chunkList) throws Exception {
		System.out.println("Writer Thread " + Thread.currentThread().getName());
		productRepository.saveAll(chunkList);
	}
}
