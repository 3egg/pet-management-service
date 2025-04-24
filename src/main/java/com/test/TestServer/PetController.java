package com.test.TestServer;

import com.test.TestServer.entity.Pet;
import com.test.TestServer.repository.PetRepository;
import jakarta.validation.Valid;
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
public class PetController {

    /**
     * The pet repository. This is used to interact with the database.
     */
    private final PetRepository petRepository;

    public PetController(@NonNull PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    /**
     * Gets all of the pets in the database.
     * @return All of the pets in the database.
     */
    @GetMapping
    public List<Pet> getPets() {
        log.info("PetController.getPets() called");
        return petRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Pet> savePet(@Valid @RequestBody Pet pet) {
         return ResponseEntity.ok(petRepository.save(pet));
    }
}
