package kg.attractor.jobsearchjava27.service.impl;

import kg.attractor.jobsearchjava27.dto.ImageDto;
import kg.attractor.jobsearchjava27.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    @Override
    public ResponseEntity<?> getById(String name) {
        return getOutputFile(name,"images", MediaType.IMAGE_JPEG);
    }

    @Override
    public void create(ImageDto imageDto) {
        String name = saveUploadedFile(imageDto.getFile(), "images");
        System.out.println(name);
    }
}
