package projeto.gestaotarefas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> findTasksByUserId(Long userId) {
        return taskRepository.findByUserId(userId);
    }

    public void save(Task task) {
        taskRepository.save(task);
    }

    public void delete(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    public Optional<Task> findById(Long taskId) {
        return taskRepository.findById(taskId);
    }

    public boolean existsById(Long taskId) {
        return taskRepository.existsById(taskId);
    }

    public List<Task> findTasksByStatus(String status) {
        return taskRepository.findByStatus(status);
    }

    public void concluirTarefa(Long taskId) {
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            task.setConcluida(true);
            taskRepository.save(task);
        } else {
            throw new EntityNotFoundException("Tarefa não encontrada com o ID: " + taskId);
        }
    }
}
