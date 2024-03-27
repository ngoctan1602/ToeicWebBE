package com.tantan.ToeicWeb.services.image;

import org.springframework.web.multipart.MultipartFile;

public interface ICloudServices {
    public String uploadFile(MultipartFile multipartFile, String folderName);
    public String uploadFileImage(MultipartFile multipartFile,String folderName, String type);
}
