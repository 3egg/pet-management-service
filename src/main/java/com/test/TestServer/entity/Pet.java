package com.test.TestServer.entity;

import com.test.TestServer.enums.Species;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * The pet entity. This is a simple entity that has a name, species, and age.
 * It also has an owner ID, which is the ID of the owner that owns this pet.
 * A pet can only have one owner.
 */
@Getter
@Setter
@Entity
public class Pet {

    /**
     * The ID of the pet. This is the primary key of the pet table.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the pet.
     */
    @Column
    private String name;

    /**
     * The species of the pet.
     */
    @Column(nullable = false)
    @NotNull(message = "Species is required")
    private Species species;

    /**
     * The age of the pet.
     */
    @Min(value = 0, message = "Age cannot be negative")
    @Max(value = 100, message = "Age cannot exceed 100 years")
    private Integer age;

    /**
     * The ID of the owner that owns this pet.
     */
    @Column
    private Long ownerId;
}
