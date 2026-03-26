package kg.attractor.jobsearchjava27.dao;

import kg.attractor.jobsearchjava27.model.User;
import kg.attractor.jobsearchjava27.model.Vacancy;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class VacancyDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Vacancy> getVacancyById(int id) {
        String sql = "SELECT * FROM vacancies WHERE id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), id);
    }

    public List<Vacancy> getRespondedVacancy(int userId) {
        String sql = """
                select * from responded_applicants ra
                join resumes r on ra.resume_id = r.id
                join vacancies v on ra.vacancy_id = v.id
                where r.applicant_id = ?
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), userId);
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
        String sql = "set name = ?, description = ?, category_id = ?, salary = ?, exp_from = ?, exp_to = ?, created_date = ?, where id = ? ";
        jdbcTemplate.update(sql,
                vacancy.getName(),
                vacancy.getDescription(),
                vacancy.getCategoryId(),
                vacancy.getSalary(),
                vacancy.getExpFrom(),
                vacancy.getExpTo(),
                vacancy.getCreatedTime(),
                vacancy.getId());
    }

    public Vacancy createVacancy(Vacancy vacancy) {
        String sql = "insert into vacancies(name, description,  category_id, salary, exp_from, exp_to, author_id, created_date)\n)" +
                " values (:name, :description, :category_id, :salary, :exp_from, :exp_to, :author_id, :created_date)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("name", vacancy.getName());
        sqlParameterSource.addValue("description", vacancy.getDescription());
        sqlParameterSource.addValue("category_id", vacancy.getCategoryId());
        sqlParameterSource.addValue("salary", vacancy.getSalary());
        sqlParameterSource.addValue("exp_from", vacancy.getExpFrom());
        sqlParameterSource.addValue("exp_to", vacancy.getExpTo());
        sqlParameterSource.addValue("author_id", vacancy.getAuthorId());
        sqlParameterSource.addValue("created_date", vacancy.getCreatedTime());
        namedParameterJdbcTemplate.update(sql, sqlParameterSource, keyHolder);
        vacancy.setId(keyHolder.getKey().intValue());
        return vacancy;
    }

    public void deleteVacancy(int id) {
        String sql = "delete from vacancies where id = ?";
        jdbcTemplate.update(sql, id);

    }


}
