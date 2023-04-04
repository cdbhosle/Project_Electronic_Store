package com.shruteekatech.electronic.store.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="categories")
public class Category {

    @Id
    @Column(name="id")
    private int categoryId;
    @Column(name="category_title",length = 70,nullable = false)
    private String title;
    @Column(name = "category_description",length = 500)
    private String description;

    private  String coverImage;



}
