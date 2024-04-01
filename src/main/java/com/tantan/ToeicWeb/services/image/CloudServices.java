package com.tantan.ToeicWeb.services.image;

import com.cloudinary.Cloudinary;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudServices implements ICloudServices {
    @Resource
    private Cloudinary cloudinary;

    @Override
    public String uploadFile(MultipartFile multipartFile, String folderName) {
        try {
            HashMap<Object, Object> options = new HashMap<>();
            options.put("folder", folderName);
            options.put("resource_type", "video");
            Map<?, ?> uploadedFile = cloudinary.uploader().upload(multipartFile.getBytes(), options);
            String publicId = (String) uploadedFile.get("public_id");
            return cloudinary.url().secure(true).generate(publicId);


        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String uploadFileImage(MultipartFile multipartFile, String folderName, String type) {
        try {
            HashMap<Object, Object> options = new HashMap<>();
            options.put("folder", folderName);
            options.put("resource_type", type);
            if (multipartFile != null) {
                Map<?, ?> uploadedFile = cloudinary.uploader().upload(multipartFile.getBytes(), options);
                String publicId = (String) uploadedFile.get("public_id");
                return cloudinary.url().secure(true).generate(publicId);
            }
            return null;


        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
