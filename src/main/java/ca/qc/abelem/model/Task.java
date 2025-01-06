package ca.qc.abelem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;


@Entity
public class Task {
    private Long id;

    @Getter
    @Column(nullable = false)
    private String task;

    @Getter
    @Column(nullable = false)
    private LocalDate dueDate;

    public Task() {}

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}