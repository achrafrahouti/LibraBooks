package com.rest.restApi.reposotiry;

import java.util.List;

import com.rest.restApi.entities.Category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long>{
    
    List<Category> findByName(String name);
}