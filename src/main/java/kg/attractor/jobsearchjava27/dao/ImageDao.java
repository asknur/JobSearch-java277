package kg.attractor.jobsearchjava27.dao;

import kg.attractor.jobsearchjava27.model.Image;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ImageDao {
    private final JdbcTemplate jdbcTemplate;


    public void save(Long userId, String fileName) {
        String sql = "INSERT INTO images (user_id, result_file_name) VALUES (?, ?)";
        jdbcTemplate.update(sql, userId, fileName);
    }

    public Optional<Image> findByUserId(Long userId) {
        String sql = "SELECT * FROM images WHERE user_id = ?";
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Image.class), userId)
                )
        );
    }


}
