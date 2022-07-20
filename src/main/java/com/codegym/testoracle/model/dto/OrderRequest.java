package com.codegym.testoracle.model.dto;

import com.codegym.testoracle.model.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
        private Integer quantity;

        private Product product;
}
