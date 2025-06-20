package com.example.outsourcing_project.task.repository;

import com.example.outsourcing_project.task.domain.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Page<Task> findAllByIsDeletedFalse(Pageable pageable);

    Page<Task> findByTitleContainingOrDescriptionContainingAndIsDeletedFalse(
            String title, String desc, Pageable pageable);
}
