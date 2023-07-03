package com.ethanshearer.photoz.clone.repository;

import com.ethanshearer.photoz.clone.model.AuthToken;
import com.ethanshearer.photoz.clone.model.Photo;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AuthTokenRepositoy extends CrudRepository<AuthToken, Integer> {
    AuthToken findByToken(UUID token);
}
