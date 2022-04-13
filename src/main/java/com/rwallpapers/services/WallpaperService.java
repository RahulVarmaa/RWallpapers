package com.rwallpapers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rwallpapers.entity.Category;
import com.rwallpapers.entity.Wallpaper;
import com.rwallpapers.enums.ResponseCodes;
import com.rwallpapers.http.GenericResponse;
import com.rwallpapers.repositories.WallpaperRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class WallpaperService {
	
	@Autowired private WallpaperRepository wallpaperRepo;
    
	public Mono<GenericResponse<?>> saveWallpaper(Wallpaper wallpaper) {
		return Mono.create(sink -> {
			wallpaperRepo.findByImageUrl(wallpaper.getImageUrl())
			.switchIfEmpty(Mono.fromRunnable(()->{
				wallpaper.setActive(true);
				wallpaperRepo.save(wallpaper).subscribe(savedWallpaper->{
					sink.success(GenericResponse.builder().code(ResponseCodes.OK.name()).body(savedWallpaper).message("Wallpaper Saved SuccessFully").build());
				},err->{
					sink.success(GenericResponse.builder().code(ResponseCodes.ERR.name()).body(new Wallpaper()).message("Error In Saving Wallpaper").build());
				});
			}))
			.subscribe(fetchedWallpaper ->{
				sink.success(GenericResponse.builder().code(ResponseCodes.OK.name()).body(fetchedWallpaper).message("Wallpaper Already Exists").build());
			},err ->{
				sink.success(GenericResponse.builder().code(ResponseCodes.ERR.name()).body(new Category()).message("Something Went Wrong").build());
			});
		});
	}   

	public Flux<Wallpaper> getWallpapersByCategoryName(String categoryName){
		return wallpaperRepo.findByCategoryName(categoryName);
	}

	
}
