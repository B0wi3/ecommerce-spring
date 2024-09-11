package com.personal.project.ecomerce_backend.model.dao;

import com.personal.project.ecomerce_backend.model.VerificationToken;
import org.springframework.data.repository.ListCrudRepository;

public interface VerificationTokenDAO extends ListCrudRepository<VerificationToken, Long> {
}
