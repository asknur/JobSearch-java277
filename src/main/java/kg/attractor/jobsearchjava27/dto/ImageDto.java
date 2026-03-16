package kg.attractor.jobsearchjava27.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class ImageDto {
    private MultipartFile file;
    private long UserId;

}
