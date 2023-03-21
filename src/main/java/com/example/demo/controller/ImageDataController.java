package com.example.demo.controller;

import com.example.demo.service.ImageDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path="/api/v1/image")
@CrossOrigin
public class ImageDataController {
    private final ImageDataService imageDataService;

    public ImageDataController(ImageDataService imageDataService) {
        this.imageDataService = imageDataService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Long> uploadImage(@RequestParam("image")MultipartFile file, @RequestParam("name") String name) throws IOException {
        return ResponseEntity.ok(imageDataService.uploadImage(file,name));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> downloadImage(@PathVariable Long id) throws IOException {
        byte[] imageData = imageDataService.downloadImage(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }
    @GetMapping()
    public ResponseEntity<List<byte[]>> downloadAllImages() throws IOException {
        return ResponseEntity .status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png")).body(imageDataService.downloadAllImages());
    }
}
