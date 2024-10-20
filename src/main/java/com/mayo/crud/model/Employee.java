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
@Table(name = "employees")
@JsonInclude(JsonInclude.Include.NON_NULL) // Skip null fields
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;
    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "First Name is mandatory")
    private String firstName;
    @Column(name = "last_name", nullable = false)
    @NotBlank(message = "Last Name is mandatory")
    private String lastName;
    @Column(name = "email", nullable = false)
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;
    @JsonIgnore
    @Column(name = "created_date", nullable = false, updatable = false)
    // Prevents this field from being included in insert statements
    private LocalDateTime createdDate;
    @JsonIgnore
    @Column(name = "updated_date", nullable = false) // Prevents this field from being included in insert statements
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
