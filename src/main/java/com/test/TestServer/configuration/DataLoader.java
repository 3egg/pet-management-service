package com.test.TestServer.configuration;

import com.test.TestServer.entity.Owner;
import com.test.TestServer.entity.Pet;
import com.test.TestServer.enums.Species;
import com.test.TestServer.repository.OwnerRepository;
import com.test.TestServer.repository.PetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * The data loader. This is a simple component that is used to populate the database with some data.
 */
@Slf4j
@Component
public class DataLoader implements CommandLineRunner {

    private final PetRepository petRepository;
    private final OwnerRepository ownerRepository;

    public DataLoader(PetRepository petRepository, OwnerRepository ownerRepository) {
        this.petRepository = petRepository;
        this.ownerRepository = ownerRepository;
    }

    /**
     * Runs the data loader. This adds some pets to the database, and anything else you may require.
     *
     * @param args The command line arguments.
     */
    @Override
    public void run(String... args) {
        log.info("Populating database...");

        log.info("Adding pets...");
        addPets();

        log.info("Database populated!");
    }

    /**
     * Adds some pets to the database. Feel free to alter this if it doesn't fit your needs.
     */
    public void addPets() {
        Owner li = new Owner();
        li.setId(1L);
        li.setNameFirst("L");
        li.setNameLast("I");
        li.setAddress("Hong Kong");
        Owner tian = new Owner();
        tian.setId(2L);
        tian.setNameFirst("TI");
        tian.setNameLast("AN");
        tian.setAddress("Guang Zhou");
        ownerRepository.save(li);
        ownerRepository.save(tian);
        Pet dog = new Pet();
        dog.setName("Spot");
        dog.setSpecies(Species.dog);
        dog.setAge(2);
        dog.setOwner(li);
        petRepository.save(dog);

        Pet cat = new Pet();
        cat.setName("Mittens");
        cat.setSpecies(Species.cat);
        cat.setAge(3);
        cat.setOwner(li);
        petRepository.save(cat);

        Pet rabbit = new Pet();
        rabbit.setName("Bun");
        rabbit.setSpecies(Species.rabbit);
        rabbit.setAge(1);
        rabbit.setOwner(li);
        petRepository.save(rabbit);

        Pet hamster = new Pet();
        hamster.setName("Hammy");
        hamster.setSpecies(Species.hamster);
        hamster.setAge(1);
        hamster.setOwner(tian);
        petRepository.save(hamster);

        Pet bird = new Pet();
        bird.setName("Tweety");
        bird.setSpecies(Species.bird);
        bird.setAge(1);
        bird.setOwner(tian);
        petRepository.save(bird);

    }
}
