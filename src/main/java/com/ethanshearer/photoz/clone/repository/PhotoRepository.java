package com.ethanshearer.photoz.clone.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import com.ethanshearer.photoz.clone.model.Photo;


public interface PhotoRepository extends CrudRepository<Photo, Integer> {
}