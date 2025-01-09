package ca.qc.abelem.controller;

import ca.qc.abelem.model.Task;
import ca.qc.abelem.repo.TaskRepo;
import ca.qc.abelem.utils.ValidationException;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {
    @Mock
    private TaskRepo taskRepo;

    @InjectMocks
    private TaskController controller; //inject all @Mock objects inside this object

    private AutoCloseable autoCloseable;

    private final String NOT_AT_THIS_POINT = "Should not be in this point";

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }
    @Test
    void should_not_save_task_without_description() {
        Task todo = new Task();
        todo.setDueDate(LocalDate.now());

        try {
            controller.save(todo);
            fail(NOT_AT_THIS_POINT);
        } catch (ValidationException validationException) {
            assertEquals("Fill the task description", validationException.getMessage());
        }
    }

    @Test
    void should_not_save_task_without_date() {
        Task todo = new Task();
        todo.setTask("Task 01");
        try {
            controller.save(todo);
            fail(NOT_AT_THIS_POINT);
        } catch (ValidationException validationException) {
            assertEquals("Fill the due date", validationException.getMessage());
        }
    }

    @Test
    void should_not_save_task_with_a_pass_date() {
        Task todo = new Task();
        todo.setDueDate(LocalDate.now().minusYears(1));
        todo.setTask("Task 01");
        try {
            controller.save(todo);
            fail(NOT_AT_THIS_POINT);
        } catch (ValidationException validationException) {
            assertEquals("Due date must not be in past", validationException.getMessage());
        }
    }

    @Test
    void should_save_a_task() throws ValidationException {
        Task todo = new Task();
        todo.setTask("Task 01");
        todo.setDueDate(LocalDate.now());
        controller.save(todo);
        Mockito.verify(taskRepo).save(todo);
    }

}