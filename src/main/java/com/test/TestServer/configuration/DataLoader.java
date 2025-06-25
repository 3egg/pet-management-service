package com.test.TestServer.configuration;

import com.test.TestServer.entity.Owner;
import com.test.TestServer.entity.Pet;
import com.test.TestServer.enums.Species;
import com.test.TestServer.repository.OwnerRepository;
import com.test.TestServer.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The data loader. This is a simple component that is used to populate the database with some data.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final PetRepository petRepository;
    private final OwnerRepository ownerRepository;

    /**
     * Runs the data loader. This adds some pets to the database, and anything else you may require.
     *
     * @param args The command line arguments.
     */
    @Override
    public void run(String... args) {
        log.info("Populating database...");

        log.info("Adding pets...");
        initData();

        log.info("Database populated!");
    }

    /**
     * Adds some pets to the database. Feel free to alter this if it doesn't fit your needs.
     */
    @Transactional
    public void initData() {
        Owner owner = new Owner();
        owner.setAddress("He nan");
        owner.setNameFirst("Shi");
        owner.setNameLast("han da");

        Owner owner2 = new Owner();
        owner2.setAddress("Guang Dong");
        owner2.setNameFirst("Li");
        owner2.setNameLast("tian");
        ownerRepository.saveAll(List.of(owner, owner2));

        Set<Pet> pets = new HashSet<>();
        Pet dog = new Pet();
        dog.setName("Spot");
        dog.setSpecies(Species.dog);
        dog.setAge(2);
        dog.setOwner(owner);
        pets.add(dog);

        Pet cat = new Pet();
        cat.setName("Spot");
        cat.setSpecies(Species.cat);
        cat.setAge(3);
        cat.setOwner(owner2);
        pets.add(dog);

        Pet rabbit = new Pet();
        rabbit.setName("Bun");
        rabbit.setSpecies(Species.rabbit);
        rabbit.setAge(1);
        pets.add(rabbit);

        Pet hamster = new Pet();
        hamster.setName("Hammy");
        hamster.setSpecies(Species.hamster);
        hamster.setAge(1);
        pets.add(hamster);

        Pet bird = new Pet();
        bird.setName("Tweety");
        bird.setSpecies(Species.bird);
        bird.setAge(1);
        pets.add(bird);

        petRepository.saveAll(pets);
    }
}
