package com.todo.list.todo_list.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.todo_list.model.Comentario;
import com.todo.list.todo_list.services.ComentarioService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ComentarioController {
        private final ComentarioService service;

        @PostMapping("/tarefas/{tarefaId}/comentarios")
        public ResponseEntity<Comentario> adicionar(@PathVariable Long tarefaId, @RequestBody Comentario comentario) {
                return ResponseEntity.ok(service.adicionar(tarefaId, comentario));
        }
        
}
