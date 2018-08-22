package de.rieckpil.learning.springbootmultidatasources;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Test result = new Test();
        result.setId(resultSet.getLong("id"));
        result.setText(resultSet.getString("text"));
        return result;
    }
}
