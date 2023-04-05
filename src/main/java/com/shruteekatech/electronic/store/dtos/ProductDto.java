package com.shruteekatech.electronic.store.dtos;

import lombok.*;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductDto {
    private Long productId;

    private String title;

    private String description;

    private int price;

    private int quantity;

    private int discountedPrice;
    private Date addDate;

    private boolean live;

    private boolean stock;

}
