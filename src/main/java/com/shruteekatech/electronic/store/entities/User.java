package com.shruteekatech.electronic.store.entities;

import com.shruteekatech.electronic.store.dtos.BaseEntity;
import lombok.*;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    private int userId;
    @Column(name = "user_name")
    private String name;

    @Column(name = "user_email", unique = true)
    private String email;

    @Column(name = "user_password", length = 10)
    private String password;
    @Column
    private String gender;

    @Column(length = 100)
    private String about;

    @Column(name = "user_image_name")
    private String imageName;
}
