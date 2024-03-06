package projeto.gestaotarefas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Task>> getTasksByUserId(@PathVariable Long userId) {
        List<Task> tasks = taskService.findTasksByUserId(userId);
        return ResponseEntity.ok(tasks);
    }

    @PostMapping
    public ResponseEntity<String> createTask(@RequestBody Task task) {
        taskService.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).body("Tarefa criada com sucesso!");
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<String> updateTask(@PathVariable Long taskId, @RequestBody Task task) {
        if (!taskService.existsById(taskId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada!");
        }
        task.setId(taskId);
        taskService.save(task);
        return ResponseEntity.ok("Tarefa atualizada com sucesso!");
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Long taskId) {
        if (!taskService.existsById(taskId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada!");
        }
        taskService.delete(taskId);
        return ResponseEntity.ok("Tarefa removida com sucesso!");
    }

    @GetMapping("/filter/{status}")
    public ResponseEntity<List<Task>> filterTasksByStatus(@PathVariable String status) {
        List<Task> tasks = taskService.findTasksByStatus(status);
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/{taskId}/concluir")
    public ResponseEntity<String> concluirTarefa(@PathVariable Long taskId) {
        if (!taskService.existsById(taskId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada!");
        }
        taskService.concluirTarefa(taskId);
        return ResponseEntity.ok("Tarefa marcada como concluída!");
    }
}
