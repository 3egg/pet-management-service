package com.test.TestServer.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.TestServer.entity.FilterRequest;
import com.test.TestServer.entity.Owner;
import com.test.TestServer.entity.Pet;
import com.test.TestServer.exceptions.OwnerNotFoundException;
import com.test.TestServer.exceptions.PetHasOwnerException;
import com.test.TestServer.exceptions.PetNotFoundException;
import com.test.TestServer.repository.OwnerRepository;
import com.test.TestServer.repository.PetRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.aspectj.weaver.ast.Literal;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
@Transactional
public class PetService {

    private final PetRepository petRepository;
    private final OwnerRepository ownerRepository;
    private final ObjectMapper objectMapper;

    public Owner updatePetRelationships(Long petId, Long ownerId) {
        Owner owner = ownerRepository.findById(ownerId).orElseThrow(() -> new OwnerNotFoundException("Owner not found " + ownerId));
        Pet pet = petRepository.findById(petId).orElseThrow(() -> new PetNotFoundException("Pet not found " + petId));
        if (pet.getOwner() == null) {
            Set<Pet> pets = owner.getPets();
            pets.add(pet);
            ownerRepository.save(owner);
            return ownerRepository.save(owner);
        } else {
            throw new PetHasOwnerException("This pet already has the owner: " + petId);
        }
    }

    public List<Pet> filterPets(String conditions, String sort) {
        List<FilterRequest> conditionFilter = convertJsonToList(conditions);
        List<FilterRequest> sortFilter = convertJsonToList(sort);
        var orders = sortFilter.stream()
                .map(s -> new Sort.Order(Sort.Direction.fromString(s.getValue()), s.getKey()))
                .toList();
        return petRepository.findAll(getSpecification(conditionFilter), Sort.by(orders));
    }

    public Specification<Pet> getSpecification(List<FilterRequest> filterRequests) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.and(filterRequests.stream().map(f -> criteriaBuilder.equal(root.get(f.getKey()), f.getValue())).toList().toArray(new Predicate[0]));
    }


    @SneakyThrows
    private List<FilterRequest> convertJsonToList(String jsonString) {
        TypeReference<List<FilterRequest>> jacksonTypeReference = new TypeReference<>() {};
        return objectMapper.convertValue(jsonString, jacksonTypeReference);
    }
}
