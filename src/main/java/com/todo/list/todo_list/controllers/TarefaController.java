package com.todo.list.todo_list.controllers;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.todo.list.todo_list.model.StatusTarefa;
import com.todo.list.todo_list.model.Tarefa;
import com.todo.list.todo_list.services.TarefaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tarefas")
@RequiredArgsConstructor 
public class TarefaController {

    private final TarefaService service;

    @PostMapping
    public ResponseEntity<Tarefa> criar(@RequestBody Tarefa tarefa) {
        return ResponseEntity.ok(service.criar(tarefa));
    }

    @PostMapping("/usuario/{usuarioId}")
    public ResponseEntity<Tarefa> criarComUsuario(@PathVariable Long usuarioId, @RequestBody Tarefa tarefa) {
        return ResponseEntity.ok(service.criarComUsuario(tarefa, usuarioId));
    }

    @GetMapping
    public ResponseEntity<Page<Tarefa>> listarTodas(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(service.listarTodas(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Tarefa>> listarPorStatus(@PathVariable StatusTarefa status) {
        return ResponseEntity.ok(service.listarPorStatus(status));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Tarefa>> buscarPorTitulo(@RequestParam String titulo) {
        return ResponseEntity.ok(service.buscarPorTitulo(titulo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizar(@PathVariable Long id, @RequestBody Tarefa tarefa) {
        return ResponseEntity.ok(service.atualizar(id, tarefa));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Tarefa> atualizarStatus(@PathVariable Long id, @RequestBody StatusTarefa novoStatus) {
        return ResponseEntity.ok(service.atualizarStatus(id, novoStatus));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/contagem/status")
    public ResponseEntity<Long> contarPorStatus(@RequestParam StatusTarefa status) {
        return ResponseEntity.ok(service.contarPorStatus(status));
    }
}