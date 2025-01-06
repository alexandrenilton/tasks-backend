package ca.qc.abelem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.qc.abelem.model.Task;
import ca.qc.abelem.repo.TaskRepo;
import ca.qc.abelem.utils.DateUtils;
import ca.qc.abelem.utils.ValidationException;

@RestController
@RequestMapping(value ="/todo")
public class TaskController {

    @Autowired
    private TaskRepo todoRepo;

    @GetMapping
    public List<Task> findAll() {
        return todoRepo.findAll();
    }

    @PostMapping
    public ResponseEntity<Task> save(@RequestBody Task todo) throws ValidationException {
        if(todo.getTask() == null || todo.getTask() == "") {
            throw new ValidationException("Fill the task description");
        }
        if(todo.getDueDate() == null) {
            throw new ValidationException("Fill the due date");
        }
        if(!DateUtils.isEqualOrFutureDate(todo.getDueDate())) {
            throw new ValidationException("Due date must not be in past");
        }
        Task saved = todoRepo.save(todo);
        return new ResponseEntity<Task>(saved, HttpStatus.CREATED);
    }
}