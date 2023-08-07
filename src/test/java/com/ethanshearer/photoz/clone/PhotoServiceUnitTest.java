package com.ethanshearer.photoz.clone;

import com.ethanshearer.photoz.clone.exceptions.EntityNotFoundException;
import com.ethanshearer.photoz.clone.model.Photo;
import com.ethanshearer.photoz.clone.model.User;
import com.ethanshearer.photoz.clone.repository.PhotoRepository;
import com.ethanshearer.photoz.clone.repository.UserRepository;
import com.ethanshearer.photoz.clone.service.PhotoService;
import com.ethanshearer.photoz.clone.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.when;

public class PhotoServiceUnitTest {
    private PhotoRepository photoRepository;
    private PhotoService photoService;

    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setup() {
        userService = Mockito.mock(UserService.class);

        photoRepository = Mockito.mock(PhotoRepository.class);
        photoService = new PhotoService(photoRepository, userService);
    }

    @Test
    public void getAllPhotos_should_return_photos_the_principle_is_allowed_to_see() {
        // Given a user
        User user = new User("egg@head.com", "password");
        user.setId(23);

        // with a single photo
        Photo photo = new Photo();
        photo.setId(1);
        photo.setUserId(user.getId());
        List<Photo> usersPhotos = Arrays.asList(photo);

        // And another user who is the given users friend
        User otherUser = new User("other@user.com", "password");
        otherUser.setId(45);

        // with a single photo
        Photo otherUserPhoto = new Photo();
        otherUserPhoto.setId(1);
        otherUserPhoto.setUserId(otherUser.getId());
        List<Photo> otherUserPhotos = Arrays.asList(otherUserPhoto);

        List<Photo> expectedPhotos = new ArrayList<>();
        expectedPhotos.addAll(usersPhotos);
        expectedPhotos.addAll(otherUserPhotos);

        // When calling getAllPhotos and UserService.getLoggedInUser returns a user
        when(userService.getLoggedInUser()).thenReturn(user);
        when(photoRepository.findAllByUserId(user.getId())).thenReturn(expectedPhotos);
        Iterable<Photo> actualPhotos = photoService.getPrinciplesPhotos();

        // Then the principles photos should be returned
        assertIterableEquals(expectedPhotos, actualPhotos);
    }

    @Test
    public void getPhotosByUser_should_return_photos_belonging_to_the_user() throws EntityNotFoundException {
        // Given a user
        User user = new User("egg@head.com", "password");
        user.setId(23);

        // with a single photo
        Photo photo = new Photo();
        photo.setId(1);
        photo.setUserId(23);
        List<Photo> expectedPhotos = Arrays.asList(photo);

        // When calling getPhotosByUser and UserService.getUserById returns a user
        when(userService.getUserById(user.getId())).thenReturn(user);
        when(photoRepository.findAllByUserId(user.getId())).thenReturn(expectedPhotos);
        Iterable<Photo> actualPhotos = photoService.getPhotosByUser(user.getId());

        // Then that users photos should be returned
        assertIterableEquals(expectedPhotos, actualPhotos);
    }

    @Test
    public void getPhotoByID_should_return_a_photo_with_that_id_if_user_has_permission() throws EntityNotFoundException {
        // Given a user
        User user = new User("egg@head.com", "password");
        user.setId(23);

        // with a single photo
        Photo expectedPhoto = new Photo();
        expectedPhoto.setId(1);
        expectedPhoto.setUserId(23);

        // When calling getPhotosById and UserService.getLoggedInUser returns the principle
        when(userService.getLoggedInUser()).thenReturn(user);
        when(photoRepository.findByIdAndUserId(expectedPhoto.getId(), user.getId())).thenReturn(Optional.of(expectedPhoto));
        Photo actualPhoto = photoService.getPhoto(expectedPhoto.getId());

        // Then that users photos should be returned
        assertEquals(expectedPhoto, actualPhoto);
    }
    @Test
    public void addPhoto_should_return_a_photo_belonging_to_the_principle_who_uploaded_it() {
        // Given a user
        User user = new User("egg@head.com", "password");
        user.setId(23);

        String fileName = "photo";
        String contentType = "image/jpg";
        byte[] data = "some bytes".getBytes();

        Photo expectedPhoto = new Photo();
        expectedPhoto.setUserId(user.getId());
        expectedPhoto.setContentType(contentType);
        expectedPhoto.setFileName(fileName);
        expectedPhoto.setData(data);
        expectedPhoto.setUserId(user.getId());

        // When calling addPhoto and UserService.getLoggedInUser returns the user as principle
        when(userService.getLoggedInUser()).thenReturn(user);
        when(photoRepository.save(expectedPhoto)).thenReturn(null);
        Photo actualPhoto = photoService.addPhoto(fileName, contentType, data);

        // Then a new photo with supplied data should be returned
        assertEquals(actualPhoto.getUserId(), user.getId());
        assertEquals(actualPhoto.getData(), expectedPhoto.getData());
    }
    @Test
    public void getPhotoByUserAndId_should_return_a_photo_by_its_id_and_users_id() throws EntityNotFoundException {
        // Given a user
        User user = new User("egg@head.com", "password");
        user.setId(23);

        // with a single photo
        Photo expectedPhoto = new Photo();
        expectedPhoto.setId(1);
        expectedPhoto.setUserId(23);

        // When calling getPhotosById and UserService.getLoggedInUser returns the principle
        when(userService.getLoggedInUser()).thenReturn(user);
        when(photoRepository.findByIdAndUserId(expectedPhoto.getId(), user.getId())).thenReturn(Optional.of(expectedPhoto));
        Photo actualPhoto = photoService.getPhotoByUserAndId(user.getId(), expectedPhoto.getId());

        // Then that users photos should be returned
        assertEquals(expectedPhoto, actualPhoto);
    }
}
