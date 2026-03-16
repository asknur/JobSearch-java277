package kg.attractor.jobsearchjava27.controller;

import kg.attractor.jobsearchjava27.dto.ImageDto;
import kg.attractor.jobsearchjava27.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Configuration
@RequestMapping("images")
@RequiredArgsConstructor
public class AvatarController {
    private final ImageService imageService;

    @GetMapping
    public ResponseEntity<?> getImage(@RequestParam(name = "filename")String filename){
        return imageService.getById(filename);
    }

    @PostMapping
    public HttpStatus create(@RequestBody ImageDto imageDto){
        imageService.create(imageDto);
        return HttpStatus.CREATED;
    }
}
