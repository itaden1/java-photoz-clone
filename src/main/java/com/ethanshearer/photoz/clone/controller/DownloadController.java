package com.ethanshearer.photoz.clone.controller;

import com.ethanshearer.photoz.clone.exceptions.EntityNotFoundException;
import com.ethanshearer.photoz.clone.model.Photo;
import com.ethanshearer.photoz.clone.service.PhotoService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class DownloadController {

    private final PhotoService photoService;

    public DownloadController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable Integer id) throws EntityNotFoundException {
        Photo photo = photoService.getPhoto(id);

        byte[] data = photo.getData();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(photo.getContentType()));
        ContentDisposition build = ContentDisposition
                .builder("attachment")
                .filename(photo.getFileName())
                .build();
        headers.setContentDisposition(build);
        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }

}
