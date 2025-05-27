package com.inventory_system.repository;



import com.inventory_system.entity.Admin;
import com.inventory_system.entity.Category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findByCategoryName(String categoryName); // Arama/ekleme için kullanılabilir
    
    List<Category> findByAdmin(Admin admin);


}

