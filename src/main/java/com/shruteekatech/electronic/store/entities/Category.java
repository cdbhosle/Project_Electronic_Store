package com.shruteekatech.electronic.store.entities;

import com.shruteekatech.electronic.store.dtos.BaseEntity;
import lombok.*;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="categories")
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int categoryId;
    @Column(name="category_title",length = 70,nullable = false)
    private String title;
    @Column(name = "category_description",length = 500)
    private String description;

    private  String coverImage;



}
