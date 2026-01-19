package com.todo.list.todo_list.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.list.todo_list.model.Subtarefa;

public interface SubtarefaRepository extends JpaRepository<Subtarefa, Long>{
        List<Subtarefa> findByTarefaId(Long tarefaId);
}
