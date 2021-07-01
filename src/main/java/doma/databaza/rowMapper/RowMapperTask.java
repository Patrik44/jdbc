package doma.databaza.rowMapper;


import doma.databaza.domena.Task;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RowMapperTask implements RowMapper<Task>  {


    @Override
    public Task mapRow(ResultSet resultSet, int i) throws SQLException {
        Task task = new Task();
        task.setId(resultSet.getInt("id"));
        task.setName(resultSet.getString("name"));
        task.setDescription(resultSet.getString("description"));
        task.setCreatedAt(resultSet.getTimestamp("createdAt"));
        return task;
    }
}


