package doma.databaza.controller;



import doma.databaza.domena.Task;
import doma.databaza.services.api.TaskServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping ("task")
@RestController
public class TaskRestController  {

    private final TaskServices taskServices;


    public TaskRestController(TaskServices taskServices) {
        this.taskServices = taskServices;
    }

    @PostMapping
    public ResponseEntity add (@RequestBody Task task){
        Integer id = taskServices.pridajTask(task);
        if (id != null){
            return new ResponseEntity<>(id, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity list (){
        List <Task> list = taskServices.list();
        return new ResponseEntity<> (list, HttpStatus.OK);
    }

    @GetMapping ("{id}")
    public ResponseEntity getTask (@PathVariable ("id") int id){
        Task task = taskServices.vratTask(id);
        if (task != null){
            return new ResponseEntity<>(task, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping ("{id}")
    public ResponseEntity update (@PathVariable ("id") int id, @RequestBody Task task){
        if (taskServices.vratTask(id) != null){
            task.setId(id);
            taskServices.aktualizujTask(id,task);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("neexistuje");
        }
    }

    @DeleteMapping ("{id}")
    public ResponseEntity delete (@PathVariable ("id") int id){
        if (taskServices.vratTask(id) != null){
            taskServices.vymazTask(id);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("doesn't exist");
        }
    }

}


