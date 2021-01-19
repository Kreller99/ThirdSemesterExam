package com.exam.repository;

import com.exam.model.Student;
import com.exam.model.Supervisor;
import org.springframework.data.repository.CrudRepository;

// Implementerer CrudRepository, hvori der er lavet x antal metoder i forvejen,
// som vi kan bruge til at oprette, opdatere, læse og slette en studerende.
public interface StudentRepo extends CrudRepository<Student, Long> {
}
