package com.ethanshearer.photoz.clone.repository;

import com.ethanshearer.photoz.clone.model.Follow;

public interface FollowRepository {
    Iterable<Follow> findByUser();
}
