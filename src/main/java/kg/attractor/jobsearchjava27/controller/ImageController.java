package kg.attractor.jobsearchjava27.controller;

import kg.attractor.jobsearchjava27.dto.ImageDto;
import kg.attractor.jobsearchjava27.exception.ImageNotFoundException;
import kg.attractor.jobsearchjava27.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Configuration
@RequestMapping("images")
@RequiredArgsConstructor
@RestController
public class ImageController {
    private final ImageService imageService;

    @PostMapping
    public HttpStatus upload(ImageDto imageDto) {
        imageService.upload(imageDto);
        return HttpStatus.OK;
    }

    @GetMapping("{userId}")
    public ResponseEntity<?> download(@PathVariable Long userId) throws ImageNotFoundException {
        return imageService.download(userId);
    }


    @GetMapping
    public ResponseEntity<?> getImage(@RequestParam(name = "filename")String filename){
        return imageService.getById(filename);
    }

    @PostMapping("/create")
    public HttpStatus create(@RequestBody ImageDto imageDto){
        imageService.create(imageDto);
        return HttpStatus.CREATED;
    }


}
