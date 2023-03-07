package com.project.bumawiki.domain.image.service;

import com.project.bumawiki.domain.image.exception.NoImageException;
import io.netty.util.internal.StringUtil;
import org.imgscalr.Scalr;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class ImageService {
    private static final String uploadPath = "/home/t/Desktop/image/";
    private final String file_extension = "webp";
    private final List<String> allowed_extension = Arrays.asList("bmp", "gif", "jpg", "jpeg", "png", "wbmp");

    public String saveFile(MultipartFile file, String userName) throws IOException{
        //이미지 크기 제한은 스프링 설정
        if (file.getSize() <= 0) throw new IOException("Image couldn't be empty"); //change proper Exception code
        if (allowed_extension.contains( StringUtils.getFilenameExtension(file.getOriginalFilename()))) throw new IOException("Not Allowed Format"); //ditto
        
        UUID randomStr = UUID.randomUUID();
        if (file.getOriginalFilename() == null) throw new IOException("Filename does not exist"); //ditto
        String fileName = randomStr + StringUtils.cleanPath(file.getOriginalFilename());

//        Path uploadPath = Paths.get("~/image/"+userName);
        Path uploadPath = Paths.get(ImageService.uploadPath+userName);
        if(!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = file.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);

            BufferedImage image = ImageIO.read(inputStream);
            
            if (image.getWidth() <= 500 && image.getHeight() <= 500) {
                ImageIO.write(image, file_extension, filePath.toFile());
                return fileName;
            }

            BufferedImage resizedImage = Scalr.resize(image, Scalr.Method.AUTOMATIC,(int) (image.getWidth() * 0.5),(int) (image.getHeight() * 0.5), Scalr.OP_ANTIALIAS);
            ImageIO.write(resizedImage, file_extension, filePath.toFile());

            return fileName;
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }
    public ArrayList<String> GetFileUrl(MultipartFile[] files, String DocsName) throws IOException {
        ArrayList<String> ImageUrl = new ArrayList<>();
        int i=0;
        for (MultipartFile file : files){
            String fileName = saveFile(file,DocsName);
            ImageUrl.add("<<http://bumawiki.kro.kr/api/image/display/"+DocsName+"/"+fileName+">>");
            i++;
        }
        return ImageUrl;
    }

    public Resource loadFileAsResource(String DocsName, String fileName) {
        Path uploadPath = Paths.get(ImageService.uploadPath,DocsName);
        try {
            Path filePath = uploadPath.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw NoImageException.EXCEPTION;
            }
        } catch (MalformedURLException ex) {
            throw NoImageException.EXCEPTION;
        }
    }
}
