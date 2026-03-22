package kg.attractor.jobsearchjava27.service.impl;

import kg.attractor.jobsearchjava27.dao.ImageDao;
import kg.attractor.jobsearchjava27.dto.ImageDto;
import kg.attractor.jobsearchjava27.exception.ImageNotFoundException;
import kg.attractor.jobsearchjava27.model.Image;
import kg.attractor.jobsearchjava27.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageDao imageDao;
    private static final String UPLOADED_DIR = "uploaded";

    @SneakyThrows
    public String saveUploadedFile(MultipartFile file, String subDir)  {
        String uuidFile = UUID.randomUUID().toString();
        String resultFileName = uuidFile + "_" + file.getOriginalFilename();

        Path pathDir = Paths.get(UPLOADED_DIR + subDir);
        if (!Files.exists(pathDir)) Files.createDirectories(pathDir);

        Path filePath = Paths.get(pathDir + "/" + resultFileName);
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }

        try(OutputStream os = Files.newOutputStream(filePath)) {
            os.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultFileName;

    }

    @SneakyThrows
    public ResponseEntity<?> getOutputFile(String filename, String subDir, MediaType mediaType) {
        try {
            byte[] image = Files.readAllBytes(Paths.get("/data" + subDir + "/" + filename));
            Resource resource = new ByteArrayResource(image);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                    .contentLength(resource.contentLength())
                    .contentType(mediaType)
                    .body(resource);
        } catch (NoSuchFileException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Image not found");
        }
    }

    @Override
    public ResponseEntity<?> getById(String name) {
        return getOutputFile(name,"images", MediaType.IMAGE_JPEG);
    }

    @Override
    public void create(ImageDto imageDto) {
        String name = saveUploadedFile(imageDto.getFile(), "images");
        System.out.println(name);
    }

    @Override
    public byte[] getDownloadedFile(String fileName, String subDir) throws IOException{
        return Files.readAllBytes(Paths.get(UPLOADED_DIR + subDir + "/" + subDir));
    }

    @Override
    public void upload(ImageDto imageDto) {
        String resultFileName = saveUploadedFile(imageDto.getFile(), "images");
        log.info("resultFileName uploaded image is: {}", resultFileName);
        imageDao.save(imageDto.getUserId(), resultFileName);
    }

    @Override
    public ResponseEntity<?> download(Long userId) throws ImageNotFoundException {
        Image image = imageDao.findByUserId(userId)
                .orElseThrow(ImageNotFoundException::new);
        try{
            Resource resource = new ByteArrayResource(getDownloadedFile(image.getFileName(), "images"));

            return  ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
                            + image.getFileName() + "\"")
                    .contentLength(resource.contentLength())
                    .contentType(MediaType.IMAGE_PNG)
                    .body(resource);
        } catch (IOException e){
            log.error("Error downloading image", e);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Image not found");
    }


}
