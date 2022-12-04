package com.tw.capability.gtb.demotddapplication;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {

    private TaskRepository taskRepository;

    @GetMapping
    public List<Task> fetchTasks() {
        return taskRepository.findAll();
    }


}
