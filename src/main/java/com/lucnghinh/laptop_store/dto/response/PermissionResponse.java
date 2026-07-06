package com.lucnghinh.laptop_store.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermissionResponse {
    String name;
    String description;
}
