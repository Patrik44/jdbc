package doma.databaza.services.api;


import doma.databaza.domena.Task;

import java.util.List;

public interface TaskServices {

    List <Task> list ();
    Task vratTask (Integer id);
    Integer pridajTask (Task task);
    void aktualizujTask (Integer id, Task task);
    void vymazTask (Integer id);
    void vymazTask2 (Integer id);




}
