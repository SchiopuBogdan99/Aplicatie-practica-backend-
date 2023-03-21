package com.example.demo.service;

import com.example.demo.entity.ImageData;
import com.example.demo.repository.ImageDataRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImageDataService {
    private final ImageDataRepository imageDataRepository;
    private final String FOLDER_PATH = "C:\\Bogdan\\Aplicatie practica\\pictures\\";

    public ImageDataService(ImageDataRepository imageDataRepository) {
        this.imageDataRepository = imageDataRepository;
    }

    public Long uploadImage(MultipartFile file, String name) throws IOException {
        String filePath = FOLDER_PATH+name;
        ImageData imageData = imageDataRepository.save(ImageData.builder()
                .name(name)
                .type(file.getContentType())
                .filePath(filePath)
                .build());
        file.transferTo(new File(filePath));
        if(imageData != null) {
            return imageData.getId();
        }
        return null;
    }
    public byte[] downloadImage(Long id) throws IOException {
        Optional<ImageData> imageData = imageDataRepository.findById(id);
        String filePath = imageData.get().getFilePath();
        byte[] image = Files.readAllBytes(new File(filePath).toPath());
        return image;
    }
    public List<byte[]> downloadAllImages() throws IOException {
        List<byte[]> images = new ArrayList<>();

        List<ImageData> imageDataList = imageDataRepository.findAll();
        for (ImageData i : imageDataList) {
            String filePath = i.getFilePath();
            byte[] image = Files.readAllBytes(new File(filePath).toPath());
            images.add(image);
        }
        return images;
    }
}
