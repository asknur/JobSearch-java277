package kg.attractor.jobsearchjava27.dao;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import kg.attractor.jobsearchjava27.model.User;
import kg.attractor.jobsearchjava27.model.Vacancy;
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
public class VacancyDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Optional<Vacancy> getVacancyById(Long id) {
        String sql = "SELECT * FROM vacancies WHERE id = ?";
        return Optional.ofNullable(DataAccessUtils.singleResult(
                jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), id)));
    }

    public List<Vacancy> getRespondedVacancies(Long applicantId) {
        String sql = """
            SELECT v.* FROM vacancies v
            JOIN responded_applicants ra ON v.id = ra.vacancy_id
            JOIN resumes r ON ra.resume_id = r.id
            WHERE r.applicant_id = ?
            """;
        return jdbcTemplate.query(sql, (rs, rowNum) -> Vacancy.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
//                .categoryId(rs.getLong("category_id"))
                .salary(rs.getFloat("salary"))
                .expFrom(rs.getInt("exp_from"))
                .expTo(rs.getInt("exp_to"))
                .isActive(rs.getBoolean("is_active"))
//                .authorId(rs.getLong("author_id"))
                .createdDate(rs.getTimestamp("created_date").toLocalDateTime())
                .updateTime(rs.getTimestamp("update_time").toLocalDateTime())
                .build(), applicantId);
    }

    public List<Vacancy> getAllVacancy() {
        String sql = "select * from vacancies";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class));
    }

    public List<Vacancy> getVacanciesByCategoryId(int categoryId) {
        String sql = "select * from vacancies where category_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), categoryId);
    }

    public List<User> getApplicantsByVacancyId(int vacancyId) {
        String sql = """
                select u.* from responded_applicants ra
                join resumes r on ra.resume_id = r.id
                join users u on r.applicant_id = u.id
                where ra.vacancy_id = ?
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), vacancyId);
    }

    public void updateVacancy(Vacancy vacancy) {
        String sql = """
                UPDATE vacancies SET name = ?, description = ?, category_id = ?, salary = ?,exp_from = ?, exp_to = ?, created_date = ?
                WHERE id = ?
                """;
        jdbcTemplate.update(sql,
                vacancy.getName(),
                vacancy.getDescription(),
                vacancy.getCategoryId(),
                vacancy.getSalary(),
                vacancy.getExpFrom(),
                vacancy.getExpTo(),
                vacancy.getCreatedDate(),
                vacancy.getId());
    }

    public Vacancy createVacancy(Vacancy vacancy) {
        String sql = """
                INSERT INTO vacancies (name, description, category_id, salary, exp_from, exp_to, author_id, created_date)
                VALUES (:name, :description, :category_id, :salary, :exp_from, :exp_to, :author_id, :created_date)
                """;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("name", vacancy.getName());
        sqlParameterSource.addValue("description", vacancy.getDescription());
        sqlParameterSource.addValue("category_id", vacancy.getCategoryId());
        sqlParameterSource.addValue("salary", vacancy.getSalary());
        sqlParameterSource.addValue("exp_from", vacancy.getExpFrom());
        sqlParameterSource.addValue("exp_to", vacancy.getExpTo());
        sqlParameterSource.addValue("author_id", vacancy.getAuthorId());
        sqlParameterSource.addValue("created_date", vacancy.getCreatedDate());
        namedParameterJdbcTemplate.update(sql, sqlParameterSource, keyHolder);
        vacancy.setId((long) keyHolder.getKey().intValue());
        return vacancy;
    }

    public void deleteVacancy(int id) {
        String sql = "delete from vacancies where id = ?";
        jdbcTemplate.update(sql, id);

    }

    public List<Vacancy> getVacanciesByAuthorId(Long authorId) {
        String sql = "SELECT * FROM vacancies WHERE author_id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> Vacancy.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
//                .categoryId(rs.getLong("category_id"))
                .salary(rs.getFloat("salary"))
                .expFrom(rs.getInt("exp_from"))
                .expTo(rs.getInt("exp_to"))
                .isActive(rs.getBoolean("is_active"))
//                .authorId(rs.getLong("author_id"))
                .createdDate(rs.getTimestamp("created_date").toLocalDateTime())
                .updateTime(rs.getTimestamp("update_time").toLocalDateTime())
                .build(), authorId);
    }

    public int getRespondedCountByVacancyId(Long vacancyId) {
        String sql = "SELECT COUNT(*) FROM responded_applicants WHERE vacancy_id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, vacancyId);
    }

}
