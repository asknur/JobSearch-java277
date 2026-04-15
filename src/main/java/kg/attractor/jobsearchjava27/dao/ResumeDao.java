package kg.attractor.jobsearchjava27.dao;

import kg.attractor.jobsearchjava27.model.Resume;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
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


    public Optional<Resume> getResumeById(Long id) {
        String sql = "SELECT * FROM resumes WHERE id = ?";
        return Optional.ofNullable(DataAccessUtils.singleResult
                (jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), id)));
    }

    public Optional<Resume> getResumeByCategoryId(Integer id) {
        String sql = "SELECT * FROM resumes WHERE category_id = ?";
        try {
            Resume resume = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Resume.class), id);
            return Optional.ofNullable(resume);
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }

    public List<Resume> getResumesByApplicantId(Long applicantId) {
        String sql = "SELECT * FROM resumes WHERE applicant_id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> Resume.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
//                .category(rs.getLong("category_id"))
//                .applicantId(rs.getLong("applicant_id"))
                .salary(rs.getFloat("salary"))
                .isActive(rs.getBoolean("is_active"))
                .createDate(rs.getTimestamp("created_date").toLocalDateTime())
                .updateTime(rs.getTimestamp("update_time").toLocalDateTime())
                .build(), applicantId);
    }

    public List<Resume> getAllResume() {
        String sql = "SELECT * FROM resumes";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class));
    }

    public void updateResume(Resume resume) {
        String sql = " update resumes set name = ?, category_id = ?, applicant_id = ?, salary = ?, created_date = ?, updated_time = ? where id = ? ";
        jdbcTemplate.update(sql,
                resume.getName(),
                resume.getCategory(),
                resume.getApplicant(),
                resume.getSalary(),
                resume.getCreateDate(),
                resume.getUpdateTime(),
                resume.getId());
    }

    public void createResume(Resume resume) {
        String sql = "insert into RESUMES (NAME, CATEGORY_ID, APPLICANT_ID, SALARY, CREATED_DATE)\n" +
                "values (:name, :categoryId, :applicantId, :salary, :createdDate)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("name", resume.getName());
        sqlParameterSource.addValue("categoryId", resume.getCategory());
        sqlParameterSource.addValue("applicantId", resume.getApplicant());
        sqlParameterSource.addValue("salary", resume.getSalary());
        sqlParameterSource.addValue("createdDate", resume.getCreateDate());
        namedParameterJdbcTemplate.update(sql, sqlParameterSource, keyHolder);
        resume.setId((long) keyHolder.getKey().intValue());
    }

    public void deleteResume(int id) {
        String sql = "delete from RESUMES where id = ?";
        jdbcTemplate.update(sql, id);
    }



}
