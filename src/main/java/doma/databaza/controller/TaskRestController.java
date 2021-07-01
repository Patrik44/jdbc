package doma.databaza.controller;



import doma.databaza.domena.Task;
import doma.databaza.services.api.TaskServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin (origins = "http://localhost:3000")
@RequestMapping ("task")
@RestController
public class TaskRestController  {

    private final TaskServices taskServices;


    public TaskRestController(TaskServices taskServices) {
        this.taskServices = taskServices;
    }

    @PostMapping
    public ResponseEntity addTask (@RequestBody Task task){
        Integer id = taskServices.addTask(task);

        if (id != null){
            return new ResponseEntity<>(id, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity getAll (){
        List <Task> tasks = taskServices.getAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }


    @GetMapping ("{id}")
    public ResponseEntity getTask (@PathVariable ("id") int id){
        Task task = taskServices.getTaskById(id);
        if (task != null){
            return new ResponseEntity<>(task, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping ("{id}")
    public ResponseEntity deleteTask (@PathVariable("id") int id) {
        if (taskServices.getTaskById(id) != null) {
            taskServices.deleteTask(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Task with id: " + id + " doesnt exist");
        }
    }

    @PatchMapping ("{id}")
    public ResponseEntity updateTask (@PathVariable ("id") int id, @RequestBody Task task ){
        if (taskServices.getTaskById(id) != null){
            task.setId(id);
            taskServices.updateTask(id, task);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Task with id = " + id + "doesnt exist");
        }
    }




}


