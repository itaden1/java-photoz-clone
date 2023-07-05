package com.ethanshearer.photoz.clone.service;

import com.ethanshearer.photoz.clone.model.Photo;
import com.ethanshearer.photoz.clone.model.User;
import com.ethanshearer.photoz.clone.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PhotoService {
    private final PhotoRepository photoRepository;
    @Autowired private UserService userService;

    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }


    public Iterable<Photo> getAllPhotos() {
        User user = userService.getLoggedInUser();
        return photoRepository.findAllByUserId(user.getId());
    }

    public Photo getPhoto(Integer id) {
        User user = userService.getLoggedInUser();
        return photoRepository
                .findByIdAndUserId(id, user.getId())
                .orElse(null);
    }

    public Photo addPhoto(String fileName, String contentType, byte[] data) {
        User user = userService.getLoggedInUser();
        Photo photo = new Photo();
        photo.setUserId(user.getId());
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
