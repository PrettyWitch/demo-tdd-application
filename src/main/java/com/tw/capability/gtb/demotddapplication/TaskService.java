package com.tw.capability.gtb.demotddapplication;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskService {
    private TaskRepository taskRepository;

    public List<Task> findTasks(Boolean completed) {
        List<Task> tasks = taskRepository.findAll();
        if (Objects.isNull(completed)) {
            return tasks;
        }
        if (Boolean.TRUE.equals(completed)) {
            return tasks.stream().filter(Task::getCompleted).collect(Collectors.toList());
        }
        return tasks.stream().filter(task -> !task.getCompleted()).collect(Collectors.toList());
    }
}
