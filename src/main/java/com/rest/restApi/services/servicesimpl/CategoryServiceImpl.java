package com.rest.restApi.services.servicesimpl;

import java.util.List;

import com.rest.restApi.entities.Category;
import com.rest.restApi.exceptions.CategoryAlreadyExistException;
import com.rest.restApi.exceptions.CategoryNotFoundException;
import com.rest.restApi.reposotiry.CategoryRepository;
import com.rest.restApi.services.CategoryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class CategoryServiceImpl implements CategoryService {

    private final static Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category saveCategory(Category category) throws CategoryAlreadyExistException {
        LOGGER.info("saving a Category {}", category);
        if (!categoryRepository.findByName(category.getName()).isEmpty()) {
            throw new CategoryAlreadyExistException();
        }
        category.setName(category.getName().toUpperCase());
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findAll() {
        LOGGER.info("get all categories");
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) throws CategoryNotFoundException {
        LOGGER.info("get  a Category by  id = " + id);
        if (!categoryRepository.existsById(id))
            throw new CategoryNotFoundException("No Category with id = " + id);
        return categoryRepository.getById(id);
    }

    @Override
    public List<Category> findByName(String name) {
        LOGGER.info("get a Category by name = {}", name);
        return categoryRepository.findByName(name);
    }

    @Override
    public void delete(Long id) throws CategoryNotFoundException {
        LOGGER.info("delete a Category by id = {}", id);
        if (!categoryRepository.existsById(id))
            throw new CategoryNotFoundException("No Category with id =" + id);
        categoryRepository.deleteById(id);

    }

    @Override
    public Category update(Category category, Long id) throws CategoryNotFoundException {
        LOGGER.info("Updating {}", category);
        return categoryRepository.findById(id).map(x -> {

            x.setName(category.getName());
            return categoryRepository.save(x);
        }).orElseThrow(() -> new CategoryNotFoundException(String.format("Category  With id = %s don't exist", id)));

    }
}