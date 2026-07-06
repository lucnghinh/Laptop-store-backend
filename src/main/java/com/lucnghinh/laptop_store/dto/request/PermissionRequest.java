package com.lucnghinh.laptop_store.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermissionRequest {
    String name;
    String description;
}
