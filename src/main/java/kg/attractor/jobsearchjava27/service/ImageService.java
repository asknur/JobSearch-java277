package kg.attractor.jobsearchjava27.service;

import kg.attractor.jobsearchjava27.dto.ImageDto;
import kg.attractor.jobsearchjava27.exception.ImageNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    String saveUploadedFile(MultipartFile file, String subDir);

    ResponseEntity<?> getOutputFile(String filename, String subDir, MediaType mediaType);

    ResponseEntity<?> getById(String name);

    void create(ImageDto imageDto);

    byte[] getDownloadedFile(String fileName, String subDir) throws IOException;

    void upload(ImageDto imageDto);
    ResponseEntity<?> download(Long userId) throws ImageNotFoundException;
}
