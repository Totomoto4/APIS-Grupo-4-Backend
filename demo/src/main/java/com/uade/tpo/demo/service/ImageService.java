package com.uade.tpo.demo.service;

import com.uade.tpo.demo.entity.Image;
import com.uade.tpo.demo.entity.ImageUploadResponse;
import com.uade.tpo.demo.exceptions.ImageNotAvailableException;
import com.uade.tpo.demo.exceptions.ProductNotFoundException;
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

    public ImageUploadResponse uploadImage(Image image) throws IOException {

        imageRepository.save(image);

        return new ImageUploadResponse("Image uploaded successfully");

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
    public byte[] getImage(Long productId) throws ImageNotAvailableException {
        Optional<Image> dbImage = imageRepository.findByProductId(productId);
        if (dbImage.isPresent()){
            return ImageUtil.decompressImage(dbImage.get().getImageData());
        } else {
            throw new ImageNotAvailableException();
        }
    }

    public void updateImage(Long productId, MultipartFile newImage) throws IOException {
        Optional<Image> dbImage = imageRepository.findByProductId(productId);

        if (dbImage.isPresent()){
            dbImage.get().setName(newImage.getName());
            dbImage.get().setType(newImage.getContentType());
            dbImage.get().setImageData(ImageUtil.compressImage(newImage.getBytes()));

            imageRepository.save(dbImage.get());
            System.out.println("Imagen guardada");
        } else {
            throw new ProductNotFoundException();
        }
    }

    @Transactional
    public void deleteImage(Long productId){
        imageRepository.deleteByProductId(productId);
    }
}