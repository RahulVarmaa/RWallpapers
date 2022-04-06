package com.rwallpapers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rwallpapers.entity.Wallpaper;
import com.rwallpapers.http.GenericResponse;
import com.rwallpapers.services.WallpaperService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/wallpaper")
public class WallpaperController {
	
	@Autowired private WallpaperService wallpaperService;
	
	/**
	 * Save Wallpaper In Database
	 */
	@PostMapping("/save")
	public Mono<GenericResponse<?>> saveWallpaper(@RequestBody Wallpaper wallpaper) {
		 return wallpaperService.saveWallpaper(wallpaper);
	}
	
	/**
	 * Save Wallpaper In Database
	 */
	@GetMapping("/getByCategory/{categoryName}")
	public Flux<Wallpaper> getByCategory(@PathVariable("categoryName") String categoryName) {
		 return wallpaperService.getWallpapersByCategoryName(categoryName);
	}
	
}
