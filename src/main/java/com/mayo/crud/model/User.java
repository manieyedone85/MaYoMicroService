package com.mayo.crud.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "users")
@JsonInclude(JsonInclude.Include.NON_NULL) // Skip null fields
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;
    @Column(name = "username", nullable = false)
    @NotBlank(message = "User Name is mandatory")
    private String userName;
    @Column(name = "password", nullable = false)
    @NotBlank(message = "Password is mandatory")
    private String password;
    @JsonIgnore
    @Column(name = "createdDate", nullable = false, updatable = false)
    // Prevents this field from being included in insert statements
    private LocalDateTime createdDate;
    @JsonIgnore
    @Column(name = "updatedDate", nullable = false) // Prevents this field from being included in insert statements
    private LocalDateTime updatedDate;

    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDateTime.now(); // Set the createdDate just before persisting
        this.updatedDate = LocalDateTime.now(); // Set the createdDate just before persisting
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedDate = LocalDateTime.now(); // Update the updatedDate field on each update
    }
}
