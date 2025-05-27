package com.inventory_system.service;


import com.inventory_system.entity.Admin;
import com.inventory_system.entity.Category;
import com.inventory_system.repository.CategoryRepository;
import com.inventory_system.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryByName(String name) {
        return categoryRepository.findByCategoryName(name);
    }
    public List<Category> getCategoriesByAdmin(Admin admin) {
        return categoryRepository.findByAdmin(admin);
    }


    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteById(int id) {
        categoryRepository.deleteById(id);
    }

	public void getCategoryById(int categoryId) {
		 categoryRepository.findById(categoryId);
		
	}
	public boolean hasProducts(int categoryId) {
	    return !productRepository.findByCategory_CategoryId(categoryId).isEmpty();
	}
}
