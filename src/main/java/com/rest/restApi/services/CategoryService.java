package com.rest.restApi.services;

import java.util.List;

import com.rest.restApi.entities.Category;
import com.rest.restApi.exceptions.CategoryAlreadyExistException;
import com.rest.restApi.exceptions.CategoryNotFoundException;

public interface CategoryService {
    
    Category saveCategory(Category category) throws CategoryAlreadyExistException;
    List<Category> findAll();
    Category findById(Long id) throws CategoryNotFoundException;
    List<Category> findByName(String name);
    void delete(Long id) throws CategoryNotFoundException;
    Category update(Category category,Long id) throws CategoryNotFoundException;

}