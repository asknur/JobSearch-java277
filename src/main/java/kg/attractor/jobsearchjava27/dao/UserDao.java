package kg.attractor.jobsearchjava27.dao;

import kg.attractor.jobsearchjava27.dao.mappers.UserMapper;
import kg.attractor.jobsearchjava27.model.User;
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
public class UserDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final KeyHolder keyHolder = new GeneratedKeyHolder();


    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public List<User> getByName(String name) {
        String sql = "SELECT * FROM users WHERE name LIKE :name;";
        return namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource().addValue("name", "%" + name + "%"),
                new UserMapper()
        );
    }
}