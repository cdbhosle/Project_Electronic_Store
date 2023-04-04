package com.shruteekatech.electronic.store.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {
    @Column(name="isacive_switch")
    private String isActive;
    @CreationTimestamp
    private LocalDateTime createOn;
    @Column(name="create_date",insertable = false)
    @UpdateTimestamp
    private LocalDateTime updateOn;

}
