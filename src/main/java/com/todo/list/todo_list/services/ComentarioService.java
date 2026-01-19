package com.todo.list.todo_list.services;

import org.springframework.stereotype.Service;

import com.todo.list.todo_list.model.Comentario;
import com.todo.list.todo_list.model.Tarefa;
import com.todo.list.todo_list.repositories.ComentarioRepository;
import com.todo.list.todo_list.repositories.TarefaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComentarioService {
        private final ComentarioRepository repository;
        private final TarefaRepository tarefaRepository;

        public Comentario adicionar(Long tarefaId, Comentario comentario) {
                Tarefa tarefa = tarefaRepository.findById(tarefaId)
                        .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
                
                comentario.setTarefa(tarefa);
                return repository.save(comentario);
        }
}
