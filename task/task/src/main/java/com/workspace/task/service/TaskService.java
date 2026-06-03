package com.workspace.task.service;

import com.workspace.task.model.Task;
import com.workspace.task.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

public class TaskService {

    private final TaskRepository taskRepository = new TaskRepository();

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
