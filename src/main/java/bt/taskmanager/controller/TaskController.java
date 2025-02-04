package bt.taskmanager.controller;

import bt.taskmanager.model.Task;
import bt.taskmanager.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    
    private final TaskService taskService;
    
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    
    // Create Task
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.addTask(task));
    }
    
    // Get All Tasks
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }
    
    // Get Task by ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Task>> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }
    
    // Delete Task
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
    
    // Get Highest Priority Task (Queue)
    @GetMapping("/priority")
    public ResponseEntity<Task> getHighestPriorityTask() {
        return ResponseEntity.ok(taskService.getHighestPriorityTask());
    }
    
    // Get All Categories (Set)
    @GetMapping("/categories")
    public ResponseEntity<Set<String>> getAllCategories() {
        return ResponseEntity.ok(taskService.getAllCategories());
    }
}
