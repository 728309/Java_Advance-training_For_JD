package com.example.demo.controller;

import com.example.demo.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/normal")
    public String runNormalTask() throws InterruptedException {
        return taskService.runNormalTask();
    }

    @GetMapping("/async")
    public CompletableFuture<String> runAsyncTask() {
        return taskService.runAsyncTask();
    }

    @PostMapping("/counter/increment")
    public int incrementCounter() {
        return taskService.incrementCounter();
    }

    @GetMapping("/counter")
    public int getCounter() {
        return taskService.getCounter();
    }
}