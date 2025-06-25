package com.test.TestServer.repository;

import com.test.TestServer.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The repository that manages the Pet entity.
 */
@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {

}
