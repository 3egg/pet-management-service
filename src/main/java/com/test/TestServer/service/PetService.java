package com.test.TestServer.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.TestServer.entity.FilterDto;
import com.test.TestServer.entity.Owner;
import com.test.TestServer.entity.Pet;
import com.test.TestServer.entity.SortDto;
import com.test.TestServer.exception.OwnerNotFoundException;
import com.test.TestServer.exception.PetAlreadyHasOwnerException;
import com.test.TestServer.exception.PetNotFoundException;
import com.test.TestServer.repository.OwnerRepository;
import com.test.TestServer.repository.PetRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PetService {

    private final PetRepository petRepository;
    private final OwnerRepository ownerRepository;
    private final ObjectMapper objectMapper;

    public Pet assignOwnerToPet(Long ownerId, Long petId) {
        Owner owner = ownerRepository.findById(ownerId).orElseThrow(OwnerNotFoundException::new);
        Pet pet = petRepository.findById(petId).orElseThrow(PetNotFoundException::new);
        if (pet.getOwner() != null) {
            throw new PetAlreadyHasOwnerException("The pet already has the owner!");
        }
        pet.setOwner(owner);
        return petRepository.save(pet);
    }

    public List<Pet> getPets() {
        log.info("PetService.getPets() called");
        return petRepository.findAll();
    }


    public List<Pet> filterPets(String sort, String name, Integer age) {
        FilterDto filterDto = FilterDto.builder()
                .age(age)
                .name(name)
                .build();
        List<Sort.Order> orders = jsonStringToSortDto(sort).stream()
                .map(s -> new Sort.Order(Sort.Direction.fromString(s.getDirection()), s.getField())).toList();

        Specification<Pet> specification = getSpecification(filterDto);

        return petRepository.findAll(specification, Sort.by(orders));
    }

    public Specification<Pet> getSpecification(FilterDto filterDto) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (filterDto.getAge() != null) {
                predicates.add(criteriaBuilder.equal(root.get("age"), filterDto.getAge()));
            }

            if (filterDto.getName() != null) {
                predicates.add(criteriaBuilder.equal(root.get("name"), filterDto.getName()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }

    private List<SortDto> jsonStringToSortDto(String jsonString) {
        try {
            return objectMapper.readValue(jsonString, new TypeReference<>() {
            });
        } catch (Exception e) {
            log.info("jsonStringToSortDto failed: {}", e.getMessage());
            return Collections.emptyList();
        }
    }
}
