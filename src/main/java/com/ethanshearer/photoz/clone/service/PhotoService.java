package com.ethanshearer.photoz.clone.service;

import com.ethanshearer.photoz.clone.model.Photo;
import com.ethanshearer.photoz.clone.repository.PhotoRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class PhotoService {
    private final PhotoRepository photoRepository;

    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public Iterable<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }

    public Photo getPhoto(Integer id) {
        return photoRepository.findById(id).orElse(null);
    }

    public Photo addPhoto(String fileName, String contentType, byte[] data) {
        Photo photo = new Photo();
        photo.setContentType(contentType);
        photo.setFileName(fileName);
        photo.setData(data);
        photoRepository.save(photo);
        return photo;
    }

    public void removePhoto(Integer id) {
        photoRepository.deleteById(id);
    }
}
