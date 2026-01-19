package com.todo.list.todo_list.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.todo_list.model.Subtarefa;
import com.todo.list.todo_list.services.SubtarefaService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SubtarefaController {
        
        private final SubtarefaService service;

        @PostMapping("/tarefas/{tarefaId}/subtarefas")
        public ResponseEntity<Subtarefa> criar(@PathVariable Long tarefaId, @RequestBody Subtarefa subtarefa) {
            return ResponseEntity.ok(service.adicionar(tarefaId, subtarefa));
        }
        
        @PatchMapping("/subtarefas/{id}/concluir")
        public ResponseEntity<Subtarefa> alternarStatus(@PathVariable Long id) {
                return ResponseEntity.ok(service.alternarStatus(id));
        }
}
