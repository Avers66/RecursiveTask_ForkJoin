package Avers.ToDoList.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    LocalDateTime creationTime = LocalDateTime.now(); //дата и время создания задачи
    boolean isDone = false;             //является ли задача выполненной
    String title;               //название задачи
    String description;         //описание задачи

    //public Task(){}


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
