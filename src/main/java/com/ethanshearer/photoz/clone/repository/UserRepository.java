package com.ethanshearer.photoz.clone.repository;

import com.ethanshearer.photoz.clone.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByEmailAddress(String emailAddress);
}
