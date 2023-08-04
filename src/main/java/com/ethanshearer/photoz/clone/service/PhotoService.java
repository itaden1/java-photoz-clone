package com.ethanshearer.photoz.clone.service;

import com.ethanshearer.photoz.clone.exceptions.EntityNotFoundException;
import com.ethanshearer.photoz.clone.model.Photo;
import com.ethanshearer.photoz.clone.model.User;
import com.ethanshearer.photoz.clone.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PhotoService {
    private final PhotoRepository photoRepository;
    @Autowired private UserService userService;

    public PhotoService(PhotoRepository photoRepository, UserService userService) {
        this.photoRepository = photoRepository;
        this.userService = userService;
    }

    public Iterable<Photo> getPhotosByUser(Integer userId) throws EntityNotFoundException {
        User user = userService.getUserById(userId);
        return photoRepository.findAllByUserId(user.getId());
    }

    public Iterable<Photo> getAllPhotos() {
        User user = userService.getLoggedInUser();
        return photoRepository.findAllByUserId(user.getId());
    }

    public Photo getPhoto(Integer id) throws EntityNotFoundException {
        User user = userService.getLoggedInUser();
        Photo photo = photoRepository
                .findByIdAndUserId(id, user.getId())
                .orElse(null);
        if ( photo == null ) throw new EntityNotFoundException();
        return photo;
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

    public void removePhoto(Integer id) throws EntityNotFoundException {
        User user = userService.getLoggedInUser();
        Photo photo = getPhotoByUserAndId(user.getId(), id);
        photoRepository.deleteById(photo.getId());
    }

    public Photo getPhotoByUserAndId(Integer userId, Integer photoId) throws EntityNotFoundException {
        Photo photo = photoRepository.findByIdAndUserId(userId, photoId).orElse(null);
        if (photo == null) throw new EntityNotFoundException();
        return photo;
    }
}
