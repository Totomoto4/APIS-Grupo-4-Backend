package com.uade.tpo.demo.service;

import com.uade.tpo.demo.entity.Image;
import com.uade.tpo.demo.entity.ImageUploadResponse;
import com.uade.tpo.demo.repository.ImageRepository;
import com.uade.tpo.demo.util.ImageUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public ImageUploadResponse uploadImage(MultipartFile file) throws IOException {

        imageRepository.save(Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtil.compressImage(file.getBytes())).build());

        return new ImageUploadResponse("Image uploaded successfully: " +
                file.getOriginalFilename());

    }

    @Transactional
    public Image getInfoByImageByName(String name) {
        Optional<Image> dbImage = imageRepository.findByName(name);

        return Image.builder()
                .name(dbImage.get().getName())
                .type(dbImage.get().getType())
                .imageData(ImageUtil.decompressImage(dbImage.get().getImageData())).build();

    }

    @Transactional
    public byte[] getImage(String name) {
        Optional<Image> dbImage = imageRepository.findByName(name);
        byte[] image = ImageUtil.decompressImage(dbImage.get().getImageData());
        return image;
    }


}