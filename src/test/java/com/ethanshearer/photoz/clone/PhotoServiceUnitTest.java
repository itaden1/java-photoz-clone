package com.ethanshearer.photoz.clone;

import com.ethanshearer.photoz.clone.model.Photo;
import com.ethanshearer.photoz.clone.model.User;
import com.ethanshearer.photoz.clone.repository.PhotoRepository;
import com.ethanshearer.photoz.clone.repository.UserRepository;
import com.ethanshearer.photoz.clone.service.PhotoService;
import com.ethanshearer.photoz.clone.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class PhotoServiceUnitTest {
    private PhotoRepository photoRepository;
    private PhotoService photoService;

    private UserRepository userRepository;
    private UserService userService;
    @BeforeEach
    void setup(){
        photoRepository = Mockito.mock(PhotoRepository.class);
        photoService = new PhotoService(photoRepository);

        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserService(userRepository);

    }
    @Test
    public void getPhotosOfPrinciple_should_return_photos_belonging_to_principle() {
        // Given a user
        User user = new User("egg@head.com", "password");
        user.setId(23);

        // with a single photo
        Photo photo = new Photo();
        photo.setId(1);
        photo.setUserId(23);
        List<Photo> photoList = Arrays.asList(photo);

        // When calling getAllPhotos and UserService.getLoggedInUser returns a user
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        when(photoRepository.findAllByUserId(anyInt())).thenReturn(photoList);
        Iterable<Photo> photos = photoService.getAllPhotos();
    }
}
