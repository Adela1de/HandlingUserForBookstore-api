package com.example.userHandlingForBookstoreAPI.repositories;

import com.example.userHandlingForBookstoreAPI.entities.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
}
