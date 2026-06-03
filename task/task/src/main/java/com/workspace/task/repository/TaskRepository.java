package com.workspace.task.repository;

import com.workspace.task.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskRepository {

    private final List<Task> store = new ArrayList<>();
    private long sequence = 1;

    public Task save(Task task) {
        if (task.getId() == null) {
            task.setId(sequence++);
            store.add(task);
        } else {
            store.removeIf(t -> t.getId().equals(task.getId()));
            store.add(task);
        }
        return task;
    }

    public List<Task> findAll() {
        return new ArrayList<>(store);
    }

    public Optional<Task> findById(Long id) {
        return store.stream().filter(t -> t.getId().equals(id)).findFirst();
    }

    public void deleteById(Long id) {
        store.removeIf(t -> t.getId().equals(id));
    }
}
