package doma.databaza.services.api;


import doma.databaza.domena.Task;

import java.util.List;

public interface TaskServices {
    List<Task> getAllTasks ();
    Task getTaskById (Integer id);
    Integer addTask (Task task);
    void deleteTask (Integer id);
    void updateTask (Integer id, Task task);






}
