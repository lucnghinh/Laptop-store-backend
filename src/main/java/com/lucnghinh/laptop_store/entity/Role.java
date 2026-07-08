package com.lucnghinh.laptop_store.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "roles")
@Builder
public class Role {
    @Id
    String name;

    String description;

    @ManyToMany
    Set<Permission> permissions;

}
