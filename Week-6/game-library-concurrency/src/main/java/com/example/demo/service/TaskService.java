package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class TaskService {
    private final ExecutorService executorService = Executors.newFixedThreadPool(3);
    private int counter = 0;

    public String runNormalTask() throws InterruptedException {
        Thread.sleep(3000);
        return "Normal task finished after 3 seconds";
    }

    public CompletableFuture<String> runAsyncTask() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
                return "Task was interrupted";
            }

            return "Async task finished after 3 seconds on thread: "
                    + Thread.currentThread().getName();
        }, executorService);
    }

    public synchronized int incrementCounter() {
        counter++;
        return counter;
    }

    public int getCounter() {
        return counter;
    }
}