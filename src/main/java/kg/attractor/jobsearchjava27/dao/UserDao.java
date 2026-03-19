package kg.attractor.jobsearchjava27.dao;

import kg.attractor.jobsearchjava27.dao.mappers.UserMapper;
import kg.attractor.jobsearchjava27.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public List<User> getByName(String name) {
        String sql = "SELECT * FROM users WHERE name LIKE :name";
        return namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource().addValue("name", "%" + name + "%"),
                new UserMapper()
        );
    }

    public List<User> getByPhone(String phone) {
        String sql = "SELECT * FROM users WHERE phone_number LIKE :phone_number";
        return namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource().addValue("phone_number", "%" + phone + "%"),
                new UserMapper());
    }

    public List<User> getByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email LIKE :email";
        return namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource().addValue("email", "%" + email + "%"),
                new UserMapper());
    }

    public boolean existsByEmail(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count > 0;
    }


}