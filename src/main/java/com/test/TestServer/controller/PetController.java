package com.test.TestServer.controller;

import com.test.TestServer.entity.Owner;
import com.test.TestServer.entity.Pet;
import com.test.TestServer.repository.PetRepository;
import com.test.TestServer.service.PetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
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
    private final PetRepository petRepository;
    private final PetService petService;

    /**
     * Gets all of the pets in the database.
     *
     * @return All of the pets in the database.
     */
    @GetMapping
    public ResponseEntity<List<Pet>> getPets() {
        log.info("PetController.getPets() called");
        return ResponseEntity.ok(petRepository.findAll());
    }

    @PutMapping("{petId}/assignTo/{ownerId}")
    public ResponseEntity<Owner> updatePetRelationships(@PathVariable Long petId, @PathVariable Long ownerId) {
        return ResponseEntity.ok(petService.updatePetRelationships(petId, ownerId));
    }

    @GetMapping("filter")
    public ResponseEntity<List<Pet>> filterByConditions(@RequestParam(defaultValue = "[\"key\":\"name\", \"value\":\"Spot\"}]") String conditions,
                                                        @RequestParam(defaultValue = "[\"key\":\"age\", \"value\":\"DESC\"}]") String sort) {
        return ResponseEntity.ok(petService.filterPets(conditions, sort));
    }


}
