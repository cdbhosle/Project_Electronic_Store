package com.shruteekatech.electronic.store.repositories;


import com.shruteekatech.electronic.store.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

}
