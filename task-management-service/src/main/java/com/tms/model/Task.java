package com.tms.model;

import com.tms.User;

import java.util.ArrayList;
import java.util.List;

public class Task {
    private static int uniqueId = 1;
    private int id;
    private String subtract;
    List<Subtask> subtaskList;
    User user;
    TaskType taskType;
    TaskStatus taskStatus;
    public Task(){
        this.id = uniqueId++;
        this.taskStatus = TaskStatus.OPEN;
        this.subtaskList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setSubtract(String subtract) {
        this.subtract = subtract;
    }

}
