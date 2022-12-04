package com.tw.capability.gtb.demotddapplication;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {

    private TaskService taskService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Task> fetchTasks(@RequestParam(required = false) Boolean completed) {
        return taskService.findTasks(completed);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task){
        return taskService.createTask(task);
    }

}
