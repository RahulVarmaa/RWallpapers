package com.rwallpapers.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
@Document(value = "Category")
public class Category {
   
   @Id
   private String id;
   private String name;
   @Builder.Default
   private Boolean active = false;
   @Builder.Default
   private List<Wallpaper> wallpaperList = new ArrayList<Wallpaper>();
   private String imgUrl;
   @CreatedDate
   private LocalDateTime createdOn;
   @LastModifiedDate
   private LocalDateTime lastModifiedDate;
}
