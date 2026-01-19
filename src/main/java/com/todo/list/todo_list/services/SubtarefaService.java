package com.todo.list.todo_list.services;

import org.springframework.stereotype.Service;

import com.todo.list.todo_list.model.Subtarefa;
import com.todo.list.todo_list.model.Tarefa;
import com.todo.list.todo_list.repositories.SubtarefaRepository;
import com.todo.list.todo_list.repositories.TarefaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubtarefaService {
        
        private final SubtarefaRepository subtarefaRepository;
        private final TarefaRepository tarefaRepository;

        public Subtarefa adicionar(Long tarefaId, Subtarefa subtarefa) {
                Tarefa tarefa = tarefaRepository.findById(tarefaId).orElseThrow(() -> new RuntimeException("Tarefa pai não encontrada"));

                subtarefa.setTarefa(tarefa);
                return subtarefaRepository.save(subtarefa);
        }

        public Subtarefa alternarStatus(Long id) {
                Subtarefa subtarefa = subtarefaRepository.findById(id).orElseThrow(() -> new RuntimeException("Subtarefa não encontrada"));

                subtarefa.setConcluida(!subtarefa.isConcluida());
                return subtarefaRepository.save(subtarefa);
        }
}
