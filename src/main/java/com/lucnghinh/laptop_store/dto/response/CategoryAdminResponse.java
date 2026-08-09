package com.lucnghinh.laptop_store.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryAdminResponse {
    UUID id;

    String name;

    String description;

    boolean active;
}
