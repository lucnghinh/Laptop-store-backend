package com.lucnghinh.laptop_store.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductPageResponse {
    List<ProductResponse> products;
    int page;
    int size;
    long totalElements;
    int totalPages;
    boolean first;
    boolean last;
}
