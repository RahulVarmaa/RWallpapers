package com.rwallpapers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rwallpapers.entity.Category;
import com.rwallpapers.enums.ResponseCodes;
import com.rwallpapers.http.GenericResponse;
import com.rwallpapers.repositories.CategoryRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public Mono<GenericResponse<?>> saveCategory(Category category) {
		return Mono.create(sink -> {
			categoryRepository.findByName(category.getName()).switchIfEmpty(Mono.fromRunnable(() -> {
               categoryRepository.save(category).subscribe(savedCat ->{
            	   sink.success(GenericResponse.builder().code(ResponseCodes.OK.name()).body(savedCat).message("Category Saved SuccessFully").build());
               },err->{
            	   sink.success(GenericResponse.builder().code(ResponseCodes.ERR.name()).body(new Category()).message("Error In Saving Category").build());
               });
			}))
            .subscribe(cat -> {
            	if (cat.getActive()) {
            		sink.success(GenericResponse.builder().code(ResponseCodes.OK.name()).body(category).message("Category Already Exists").build());
				} else {
					sink.success(GenericResponse.builder().code(ResponseCodes.ERR.name()).body(category).message("Deactivated Category").build());
				}
            },err->{
            	err.printStackTrace();
            	sink.success(GenericResponse.builder().code(ResponseCodes.ERR.name()).body(new Category()).message("Something Went Wrong").build());
            });
		});
	}

	public Flux<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	public Flux<Category> getActiveCategories() {
		return categoryRepository.findByActive(true);
	}
	
	public Flux<Category> getDeActivatedCategories() {
		return categoryRepository.findByActive(false);
	}
	
	
	public Mono<GenericResponse<?>> activateCategory(Category category){
		return Mono.create(sink ->{
			category.setActive(true);
			categoryRepository.save(category).subscribe(savedCategory ->{
				sink.success(GenericResponse.builder().code(ResponseCodes.OK.name()).body(savedCategory).message("Category Activated SuccessFully").build());
			},err->{
				sink.success(GenericResponse.builder().code(ResponseCodes.ERR.name()).body(new Category()).message("Unable To Activate Category").build());
			});
		});
	}
	
	
	public Mono<GenericResponse<?>> deActivateCategory(Category category){
		return Mono.create(sink ->{
			category.setActive(false);
			categoryRepository.save(category).subscribe(savedCategory ->{
				sink.success(GenericResponse.builder().code(ResponseCodes.OK.name()).body(savedCategory).message("Category DeActivated SuccessFully").build());
			},err->{
				sink.success(GenericResponse.builder().code(ResponseCodes.ERR.name()).body(new Category()).message("Unable To DeActivate Category").build());
			});
		});
	}

	

}
