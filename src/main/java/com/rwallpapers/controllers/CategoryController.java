package com.rwallpapers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rwallpapers.entity.Category;
import com.rwallpapers.http.GenericResponse;
import com.rwallpapers.services.CategoryService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired private CategoryService categoryService;
	
	/**
	 * Inserts A Category in to the Database
	 */
	@PostMapping("/save")
	public Mono<GenericResponse<?>> saveCategory(@RequestBody Category category) {
		return categoryService.saveCategory(category);
	}
	
	@PostMapping("/activate")
	public Mono<GenericResponse<?>> activateCategory(@RequestBody Category category) {
		return categoryService.activateCategory(category);
	}
	
	@PostMapping("/deActivate")
	public Mono<GenericResponse<?>> deActivateCategory(@RequestBody Category category) {
		return categoryService.deActivateCategory(category);
	}
	
	/**
	 * Returns All The Categories
	 * Both Active And Deactive
	 */
	@GetMapping("/getCategories")
	public Flux<Category> getCategories() {
		return categoryService.getAllCategories();
	}
	
	
	/**
	 * Returns Only The Active Categories
	 */
	@GetMapping("/getActiveCategories")
	public Flux<Category> getActiveCategories() {
		return categoryService.getActiveCategories();
	}
	
	/**
	 * Returns Only The Active Categories
	 */
	@GetMapping("/getDeActivatedCategories")
	public Flux<Category> getDeActivatedCategories() {
		return categoryService.getDeActivatedCategories();
	}
	

}
