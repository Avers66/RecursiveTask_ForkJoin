package Avers.ToDoList;

import Avers.ToDoList.model.Task;
import Avers.ToDoList.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(produces="application/json")
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @GetMapping("/")
    public String  index() {
        return new Date().toString();
    }


    @PostMapping(path="/tasks/", consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Task add(@RequestBody Task task) {
         taskRepository.save(task);
         return task;
    }

    @GetMapping(path="/tasks/", produces="application/json")
    public List<Task> get() {
        Iterable<Task> taskIterable = taskRepository.findAll();
        List<Task> tasks = new ArrayList<>();
        for (Task t : taskIterable) {
            tasks.add(t);
        }
        return tasks;
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> GetById(@PathVariable("id") int id) {
        Optional<Task> optTask = taskRepository.findById(id);
        if (optTask.isPresent()) {
            return new ResponseEntity<>(optTask.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PatchMapping(path="tasks/{id}", consumes="application/json")
    public ResponseEntity<Task> patch(@PathVariable("id") int id, @RequestBody Task patch) {
        Optional<Task> optTask = taskRepository.findById(id);
        if (optTask.isPresent()) {
            Task task = taskRepository.findById(id).get();
            if (patch.getTitle() != null) task.setTitle(patch.getTitle());
            if (patch.getDescription() != null) task.setDescription(patch.getDescription());
            task.setDone(patch.isDone());
            taskRepository.save(task);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("tasks/{id}")
    //@ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Task> deleteOrder(@PathVariable("id") int id) {
        Optional<Task> optTask = taskRepository.findById(id);
        if (optTask.isPresent()) {
            taskRepository.deleteById(id);
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
