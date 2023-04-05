package com.shruteekatech.electronic.store.repositories;

import com.shruteekatech.electronic.store.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    //search
    Page<Product> findByTitleContaining(String subTitle,Pageable pageable);

    Page<Product> findByLive(Pageable pageable);

    //custome methods




}
