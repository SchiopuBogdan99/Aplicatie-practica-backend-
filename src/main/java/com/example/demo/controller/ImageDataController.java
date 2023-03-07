package com.example.demo.controller;

import com.example.demo.service.ImageDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(path="/api/v1/image")
public class ImageDataController {
    private final ImageDataService imageDataService;

    public ImageDataController(ImageDataService imageDataService) {
        this.imageDataService = imageDataService;
    }

    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file, @RequestParam("name") String name) throws IOException {
        String uploadImage = imageDataService.uploadImage(file,name);
        return ResponseEntity.ok(uploadImage);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> downloadImage(@PathVariable Long id) throws IOException {
        byte[] imageData = imageDataService.downloadImage(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }
}
