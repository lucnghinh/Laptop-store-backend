package com.lucnghinh.laptop_store.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false ,unique = true, length = 30)
    String username;

    @Column(nullable = false ,length = 255)
    String password;

    @Column(nullable = false ,unique = true,length = 255)
    String email;

    @Column(nullable = false, length = 50)
    String firstName;

    @Column(nullable = false, length = 50)
    String lastName;
    LocalDate dob;

    @ManyToMany
    Set<Role> roles;
}
