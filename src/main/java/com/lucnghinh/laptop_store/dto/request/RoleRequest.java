package com.lucnghinh.laptop_store.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleRequest {
    String name;
    String description;
    Set<String> permissions;
}
