package com.eci.cosw.springbootsecureapi.service;

import java.util.List;

import com.eci.cosw.springbootsecureapi.model.TodoTask;

public interface TodoTaskService {
    List<TodoTask> getTodoList();
    void addTodo(TodoTask todo);
}