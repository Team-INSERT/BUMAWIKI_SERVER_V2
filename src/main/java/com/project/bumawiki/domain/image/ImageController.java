package com.project.bumawiki.domain.image;


import com.project.bumawiki.domain.image.payload.Response;
import com.project.bumawiki.domain.image.service.StorageService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/image")
public class ImageController {
   // private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    private StorageService storageService;

    @Autowired
    public void FileController(StorageService storageService){
        this.storageService = storageService;
    }
    @GetMapping("/display/{DocsName}/{fileName}")
    public ResponseEntity<Resource> displayImage(@PathVariable String fileName,
                                                 @PathVariable String DocsName,
                                                 HttpServletRequest request) {
        // Load file as Resource
        Resource resource = storageService.loadFileAsResource(DocsName, fileName);
        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            System.out.println(contentType);
        } catch (IOException ex) {
        }
        if(contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header("Content-Type",contentType)
                .body(resource);
    }

}
