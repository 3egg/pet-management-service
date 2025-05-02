package com.test.TestServer.controller;

import com.test.TestServer.entity.Pet;
import com.test.TestServer.service.PetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The REST controller for all things related to {@link Pet}s.
 */
@Slf4j
@RestController
@RequestMapping("pets")
@RequiredArgsConstructor
public class PetController {

    /**
     * The pet repository. This is used to interact with the database.
     */
    private final PetService petService;

    /**
     * Gets all of the pets in the database.
     *
     * @return All of the pets in the database.
     */
    @GetMapping
    public ResponseEntity<List<Pet>> getPets() {
        log.info("PetController.getPets() called");
        return ResponseEntity.ok(petService.getPets());
    }

    @PostMapping("/{petId}/assignTo/{ownerId}")
    public ResponseEntity<Pet> assignOwnerToPet(@PathVariable Long ownerId, @PathVariable Long petId) {
        log.info("PetController.assignOwnerToPet() called");
        return ResponseEntity.ok(petService.assignOwnerToPet(ownerId, petId));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Pet>> filterPets(@RequestParam(name = "sort", defaultValue = "[{\"field\":\"name\",\"direction\":\"desc\"}]") String sort,
                                                @RequestParam(name = "name", required = false) String name,
                                                @RequestParam(name = "age", required = false) Integer age) {
        return ResponseEntity.ok(petService.filterPets(sort, name, age));
    }

}
