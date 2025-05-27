package com.inventory_system.controller;


import com.inventory_system.entity.Category;
import com.inventory_system.service.AdminService;
import com.inventory_system.service.CategoryService;
import com.inventory_system.entity.Admin;
import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private AdminService adminService;

    @GetMapping
    public String listCategories(HttpSession session, Model model) {
        String email = (String) session.getAttribute("adminEmail");
        Admin admin = adminService.findByEmail(email);

        List<Category> categories = categoryService.getCategoriesByAdmin(admin);

        model.addAttribute("categories", categories);
        return "categories";
    }


    @GetMapping("/create")
    public String showCreateCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "category-create"; // templates/category-create.html
    }

    @PostMapping("/add")
    public String addCategory(@ModelAttribute Category category, HttpSession session, Model model) {
        String email = (String) session.getAttribute("adminEmail");
        Admin admin = adminService.findByEmail(email);

        Category existing = categoryService.getCategoryByName(category.getCategoryName());
        if (existing != null) {
            model.addAttribute("error", "Category already exists!");
            model.addAttribute("category", category);
            return "category-create";
        }

        category.setAdmin(admin);  // admin ilişkisi kuruluyor
        categoryService.saveCategory(category);

        return "redirect:/categories";
    }



    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable int id) {
        categoryService.deleteById(id);
        return "redirect:/categories";
    }
}
