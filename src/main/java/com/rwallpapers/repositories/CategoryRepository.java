package com.rwallpapers.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.rwallpapers.entity.Category;

import reactor.core.publisher.Flux;

@Repository
public interface CategoryRepository extends ReactiveMongoRepository<Category, String>{
	
	Flux<Category> findByName(String name);
	
	Flux<Category> findByActive(Boolean active);

}
 