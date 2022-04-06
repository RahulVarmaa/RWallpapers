package com.rwallpapers.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.rwallpapers.entity.Wallpaper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface WallpaperRepository extends ReactiveMongoRepository<Wallpaper, String>{
	
	Mono<Wallpaper> findByImageUrl(String imageUrl);
	
	Flux<Wallpaper> findByCategoryName(String categoryName);

}
