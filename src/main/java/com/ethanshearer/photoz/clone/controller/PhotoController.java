package com.ethanshearer.photoz.clone.controller;

import com.ethanshearer.photoz.clone.model.Photo;
import com.ethanshearer.photoz.clone.service.PhotoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
public class PhotoController {

    private final PhotoService photoService;

    public PhotoController(PhotoService photoService){
        this.photoService = photoService;
    }

    @GetMapping("/")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/api/photoz")
    public Iterable<Photo> get() {

        return photoService.getAllPhotos();
    }

    @GetMapping("/api/photoz/{id}")
    public Photo get(@PathVariable Integer id) {
         Photo photo = photoService.getPhoto(id);
         if (photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
         return photo;
    }

    @PostMapping("/api/photoz")
    public Photo create(@RequestPart("data") MultipartFile file) throws IOException {

        Photo photo = photoService.addPhoto(file.getOriginalFilename(), file.getContentType(), file.getBytes());
        return photo;
    }

    @DeleteMapping("/api/photoz/{id}")
    public void delete(@PathVariable Integer id) {
        photoService.removePhoto(id);
    }
}
