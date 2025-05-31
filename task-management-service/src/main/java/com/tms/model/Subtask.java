package com.tms.model;

import com.tms.User;

public class Subtask {
    private static int uniqueId=1;
    private int id;
    private String subtract;
    private User user;
    private TaskType taskType;
    private TaskStatus taskStatus;

    public Subtask(){
        this.id = uniqueId++;
        this.taskStatus = TaskStatus.OPEN;
    }

    public int getId() {
        return id;
    }

    public void setSubtract(String subtract) {
        this.subtract = subtract;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }
}
