package com.inventory_system.controller;


import com.inventory_system.entity.Category;
import com.inventory_system.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "categories"; // categories.html
    }

    @GetMapping("/create")
    public String showCreateCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "category-create"; // templates/category-create.html
    }

    @PostMapping("/add")
    public String addCategory(@ModelAttribute Category category, Model model) {
        Category existing = categoryService.getCategoryByName(category.getCategoryName());
        if (existing != null) {
            model.addAttribute("error", "Category already exists!");
            model.addAttribute("category", category); // to retain input
            return "category-create";
        }
        categoryService.saveCategory(category);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable int id) {
        categoryService.deleteById(id);
        return "redirect:/categories";
    }
}
