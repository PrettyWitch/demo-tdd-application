package com.tw.capability.gtb.demotddapplication;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {

    private TaskRepository taskRepository;

    @GetMapping
    public List<Task> fetchTasks(@RequestParam(required = false) Boolean completed) {
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
