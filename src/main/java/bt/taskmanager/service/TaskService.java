package bt.taskmanager.service;

import bt.taskmanager.model.Task;
import bt.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TaskService {
    
    private final TaskRepository taskRepository;
    
    private List<Task> taskList = new ArrayList<>();
    private Queue<Task> taskQueue = new PriorityQueue<>(Comparator.comparingInt(Task::getPriority));
    private Set<String> categories = new HashSet<>();
    private Map<Long, Task> taskMap = new HashMap<>();
    
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    
    // Create a new task
    public Task addTask(Task task) {
        taskList.add(task);
        taskQueue.offer(task);
        categories.add(task.getCategory());
        taskMap.put(task.getId(), task);
        return taskRepository.save(task);
    }
    
    // Get all tasks
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    
    // Get a task by ID
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }
    
    // Delete a task
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
        taskMap.remove(id);
    }
    
    // Get tasks by priority (Queue)
    public Task getHighestPriorityTask() {
        return taskRepository.findAll().stream()
                .min((t1, t2) -> Integer.compare(t1.getPriority(), t2.getPriority()))
                .orElse(null);
    }
    
    // Get all categories (Set)
    public Set<String> getAllCategories() {
        return taskRepository.findAll().stream()
                .map(Task::getCategory)
                .collect(Collectors.toSet());
    }
}
