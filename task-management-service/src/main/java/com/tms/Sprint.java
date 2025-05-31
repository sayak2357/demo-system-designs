package com.tms;

import com.tms.model.Task;

import java.util.ArrayList;

public class Sprint {
    private int begin,end;
    private String name;
    private ArrayList<Task> tasks;

    public Sprint(String name, int end, int begin) {
        this.name = name;
        this.end = end;
        this.begin = begin;
        this.tasks = new ArrayList<>();
    }
    public void addTask(Task task){this.tasks.add(task) ;};
    void printDetails(){
        System.out.println("Sprint "+this.name+" begins at "+this.begin+" ends at "+this.end);
    }
    final ArrayList<Task> getTasks(){return this.tasks;}
    void eraseTask(Task task){tasks.remove(task);}
}
