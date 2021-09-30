package com.rest.restApi.controllers;


import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.rest.restApi.entities.Category;
import com.rest.restApi.exceptions.CategoryAlreadyExistException;
import com.rest.restApi.exceptions.CategoryNotFoundException;
import com.rest.restApi.services.CategoryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
public class CategoryController {

    private Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);
    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "Retrieve a list of categories")
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAll() {

        LOGGER.info("Retrieve Request to get All categories");
        try {
            List<Category> categories = new ArrayList<Category>();

            categoryService.findAll().forEach(categories::add);

            if (categories.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "save a category")
    @PostMapping(value = "/categories", consumes = { "application/json" })
    public ResponseEntity<Category> create(@RequestBody @Valid Category category) throws CategoryAlreadyExistException {
        try {
            Category savedCategory = categoryService.saveCategory(category);
            return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
        } catch (CategoryAlreadyExistException e) {
            throw new CategoryAlreadyExistException("The Category " + category.getName() + " already exist");
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @ApiOperation(value = "Retrieve a category by id")
    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> getById(@PathVariable("id") Long id) throws CategoryNotFoundException {
        LOGGER.info("Request to get one category");
        Category category = categoryService.findById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @ApiOperation(value = "Update a category")
    @PutMapping("/categories/{id}")
    public ResponseEntity<Category> update(@PathVariable("id") Long id, @RequestBody Category category)
            throws CategoryNotFoundException {

        LOGGER.info("Received request to update a  category  {}", id);
        Category updated = categoryService.update(category,id);
        return new ResponseEntity<>(updated, HttpStatus.OK);

    }

    @ApiOperation(value = "Delete a category by Id")
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) throws CategoryNotFoundException {
        try {
            categoryService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CategoryNotFoundException e) {
            throw new CategoryNotFoundException("No category with id = " + id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}