package com.todo.list.todo_list.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo.list.todo_list.model.StatusTarefa;
import com.todo.list.todo_list.model.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

        long countByStatus(StatusTarefa status);
        
        List<Tarefa> findByStatus(StatusTarefa status);
        Page<Tarefa> findByStatus(StatusTarefa status, Pageable pageable);
        List<Tarefa> findByPrioridade(Integer prioridade);
        List<Tarefa> findByTituloContainingIgnoreCase(String titulo);
        List<Tarefa> findByDataVencimentoBeforeAndStatus(java.time.LocalDateTime data, StatusTarefa status);
}