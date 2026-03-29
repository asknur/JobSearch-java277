package kg.attractor.jobsearchjava27.dao;

import kg.attractor.jobsearchjava27.dao.mappers.UserMapper;
import kg.attractor.jobsearchjava27.dto.UserDto;
import kg.attractor.jobsearchjava27.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Optional<User> findById(int id) {
        String sql = "SELECT * FROM usr WHERE id = ?";
        try {
            User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM usr";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public Optional<User> getByName(String name) {
        String sql = "SELECT * FROM usr WHERE name = :name";
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("name", name);
        try {
            User user = namedParameterJdbcTemplate.queryForObject(
                    sql,
                    params,
                    new BeanPropertyRowMapper<>(User.class)
            );
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public Optional<User> getByPhone(String phone) {
        String sql = "SELECT * FROM usr WHERE phone_number = :phone_number";
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("phone_number", phone);
        try {
            User user = namedParameterJdbcTemplate.queryForObject(
                    sql,
                    params,
                    new BeanPropertyRowMapper<>(User.class)
            );
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public Optional<User> getByEmail(String email) {
        String sql = "SELECT * FROM usr WHERE email = :email";
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("email", email);
        try {
            User user = namedParameterJdbcTemplate.queryForObject(
                    sql,
                    params,
                    new BeanPropertyRowMapper<>(User.class)
            );
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public boolean existsByEmail(String email) {
        String sql = "SELECT COUNT(*) FROM usr WHERE email = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count > 0;
    }

    public void create(User user) {
        String sql = "insert into usr(email, name, password) " +
                "values(?,?,?)";

        jdbcTemplate.update(sql, user.getEmail(), user.getName(), user.getPassword());
    }

}