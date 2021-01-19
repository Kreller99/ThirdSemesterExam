package com.exam.repository;

import com.exam.model.Supervisor;
import org.springframework.data.repository.CrudRepository;


public interface SupervisorRepo extends CrudRepository<Supervisor, Long> {
}
