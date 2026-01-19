package com.todo.list.todo_list.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.list.todo_list.model.Usuario;
import com.todo.list.todo_list.repositories.UsuarioRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
        private final UsuarioRepository repository;

        @PostMapping
        public ResponseEntity<Usuario> criar(@Valid @RequestBody Usuario usuario) {
            if (repository.findByEmail(usuario.getEmail()).isPresent()) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(repository.save(usuario));
        }

        @GetMapping
        public ResponseEntity<List<Usuario>> listar() {
            return ResponseEntity.ok(repository.findAll());
        }
        
        @GetMapping("/{id}")
        public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
            return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        }
        
        
}
