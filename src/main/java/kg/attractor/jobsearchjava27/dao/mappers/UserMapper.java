package kg.attractor.jobsearchjava27.dao.mappers;

import kg.attractor.jobsearchjava27.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User u = new User();
        u.setId(rs.getInt("id"));
        u.setName(rs.getString("name"));
        u.setSurname(rs.getString("surname"));
        u.setAge(rs.getInt("age"));
        u.setEmail(rs.getString("email"));
        u.setPassword(rs.getString("password"));
        u.setPhoneNumber(rs.getString("phone_number"));
        u.setAvatar(rs.getString("avatar"));
        u.setAccountType(rs.getString("account_type"));
        return u;
    }
}
