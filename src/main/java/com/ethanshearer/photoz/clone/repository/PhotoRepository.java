package com.ethanshearer.photoz.clone.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import com.ethanshearer.photoz.clone.model.Photo;
import org.springframework.security.access.prepost.PostFilter;


public interface PhotoRepository extends CrudRepository<Photo, Integer> {
//    @PostFilter("filterObject.user.getId() == principal.id")
    Iterable<Photo> findAllByUserId(int userId);

    Optional<Photo> findByIdAndUserId(int id, int userId);
}