package kg.attractor.jobsearchjava27.dao;

import kg.attractor.jobsearchjava27.model.Resume;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ResumeDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public Optional<Resume> getResumeById(int id) {
        String sql = "SELECT * FROM resumes WHERE id = ?";
        return Optional.ofNullable(DataAccessUtils.singleResult
                (jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), id)));
    }

    public List<Resume> getResumeByCategoryId(Integer categoryId) {
        String sql = "SELECT * FROM resumes WHERE category_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), categoryId);
    }

    public List<Resume> getResumeByApplicantId(Integer applicantId) {
        String sql = "SELECT * FROM resumes WHERE applicant_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), applicantId);
    }

    public List<Resume> getAllResume() {
        String sql = "SELECT * FROM resumes";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class));
    }

    public void updateResume(Resume resume) {
        String sql = " set name = ?, category_id = ?, applicant_id = ?, salary = ?, created_date = ?, updated_time = ?, where id = ? ";
        jdbcTemplate.update(sql,
                resume.getName(),
                resume.getCategoryId(),
                resume.getApplicantId(),
                resume.getSalary(),
                resume.getCreateDate(),
                resume.getUpdateTime(),
                resume.getId());
    }

    public Resume createResume(Resume resume) {
        String sql = "insert into RESUMES (NAME, CATEGORY_ID, APPLICANT_ID, SALARY, CREATED_DATE)\n" +
                "values (:name, :categoryId, :applicantId, :salary, :createdDate)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("name", resume.getName());
        sqlParameterSource.addValue("categoryId", resume.getCategoryId());
        sqlParameterSource.addValue("applicantId", resume.getApplicantId());
        sqlParameterSource.addValue("salary", resume.getSalary());
        sqlParameterSource.addValue("created_date", resume.getCreateDate());
        namedParameterJdbcTemplate.update(sql, sqlParameterSource, keyHolder);
        resume.setId(keyHolder.getKey().intValue());
        return resume;
    }

    public void deleteResume(int id) {
        String sql = "delete from RESUMES where id = ?";
        jdbcTemplate.update(sql, id);
    }

}
