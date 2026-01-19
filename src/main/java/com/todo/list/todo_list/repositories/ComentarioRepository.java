package com.todo.list.todo_list.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.list.todo_list.model.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long>{
        
}
