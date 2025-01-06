package ca.qc.abelem.repo;

import ca.qc.abelem.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task, Long>{

}