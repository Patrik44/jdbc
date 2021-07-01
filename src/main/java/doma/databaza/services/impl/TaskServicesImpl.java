
package doma.databaza.services.impl;


import doma.databaza.domena.Task;
import doma.databaza.rowMapper.RowMapperTask;
import doma.databaza.services.api.TaskServices;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.time.Instant;
import java.util.List;

@Service
public class TaskServicesImpl implements TaskServices {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapperTask rowMapperTask = new RowMapperTask();

    public TaskServicesImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Task> getAllTasks() {
        final String sql = "select * from tasks ";
        return jdbcTemplate.query(sql, rowMapperTask);


    }

    @Override
    public Task getTaskById(Integer id) {
        final String sql = "select * from tasks where tasks.id = " + id;
        try {
            return jdbcTemplate.queryForObject(sql, rowMapperTask);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    ;


    @Override
    public Integer addTask(Task task) {
        final String sql = "insert into tasks (name, description, createdAt) values (?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, task.getName());
                preparedStatement.setString(2, task.getDescription());
                preparedStatement.setTimestamp(3, Timestamp.from(Instant.now()));
                return preparedStatement;
            }
        }, keyHolder);

        if (keyHolder != null){
            return keyHolder.getKey().intValue();
        }else return null;

    }

        @Override
        public void deleteTask (Integer id){
            final String sql = "delete from tasks where id = ?";
            jdbcTemplate.update(sql, id);

        }

        @Override
        public void updateTask (Integer id, Task task){
            final String sql = "update tasks set name = ?, description = ? where id = ?";
            jdbcTemplate.update(sql, task.getName(), task.getDescription(),id);

        }
    }

