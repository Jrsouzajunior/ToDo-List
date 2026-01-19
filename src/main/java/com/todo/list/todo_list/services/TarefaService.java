package com.todo.list.todo_list.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.todo.list.todo_list.model.Recorrencia;
import com.todo.list.todo_list.model.StatusTarefa;
import com.todo.list.todo_list.model.Tarefa;
import com.todo.list.todo_list.model.Usuario;
import com.todo.list.todo_list.repositories.TarefaRepository;
import com.todo.list.todo_list.repositories.UsuarioRepository;

@Service
public class TarefaService {
        @Autowired
        private UsuarioRepository usuarioRepository;

        @Autowired
        private TarefaRepository repository;

        public Tarefa criarComUsuario(Tarefa tarefa, Long usuarioId) {
                Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

                tarefa.setUsuario(usuario);

                if (tarefa.getTitulo() == null || tarefa.getTitulo().isEmpty()) {
                        throw new IllegalArgumentException("Título obrigatório");
                }

                return repository.save(tarefa);
        }

        public Tarefa criar(Tarefa tarefa) {
                if (tarefa == null) {
                        throw new IllegalArgumentException("A tarefa não pode ser nula");
                }
                if (tarefa.getTitulo() == null || tarefa.getTitulo().isEmpty()) {
                        throw new IllegalArgumentException("Título obrigatório");
                }
                return repository.save(tarefa);
        }

        public Page<Tarefa> listarTodas(Pageable pageable) {
                if (pageable == null) {
                        return repository.findAll(Pageable.unpaged());
                }
                return repository.findAll(pageable);
        }
        
        public Tarefa buscarPorId(Long id) {
                if (id == null) {
                        throw new IllegalArgumentException("O ID não pode ser nulo");
                }
                return repository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        }

        public List<Tarefa> buscarPorTitulo(String titulo) {
                if (titulo == null || titulo.trim().isEmpty()) {
                        return repository.findAll();
                }
                return repository.findByTituloContainingIgnoreCase(titulo);
        }

        public List<Tarefa> listarPorStatus(StatusTarefa status) {
                return repository.findByStatus(status);
        }

        public Tarefa atualizar(Long id, Tarefa tarefaAtualizada) {
                Tarefa tarefa = buscarPorId(id);

                if (tarefaAtualizada != null) {
                        if (tarefaAtualizada.getTitulo() != null) {
                                tarefa.setTitulo(tarefaAtualizada.getTitulo());
                        }
                        if (tarefaAtualizada.getPrioridade() != null) {
                                tarefa.setPrioridade(tarefaAtualizada.getPrioridade());
                        }
                }

                return repository.save(tarefa);
        }

        private void gerarProximaTarefaRecorrente(Tarefa tarefaAnterior) {
                Tarefa novaTarefa = new Tarefa();
                novaTarefa.setTitulo(tarefaAnterior.getTitulo());
                novaTarefa.setDescricao(tarefaAnterior.getDescricao());
                novaTarefa.setUsuario(tarefaAnterior.getUsuario());
                novaTarefa.setPrioridade(tarefaAnterior.getPrioridade());
                novaTarefa.setRecorrencia(tarefaAnterior.getRecorrencia());
                novaTarefa.setStatus(tarefaAnterior.getStatus());
                novaTarefa.setDataCriacao(LocalDateTime.now());

                if (tarefaAnterior.getDataVencimento() != null) {
                        LocalDateTime novaData = switch (tarefaAnterior.getRecorrencia()) {
                                case DIARIA -> tarefaAnterior.getDataVencimento().plusDays(1);
                                case SEMANAL -> tarefaAnterior.getDataVencimento().plusWeeks(1);
                                case MENSAL -> tarefaAnterior.getDataVencimento().plusMonths(1);
                                default -> tarefaAnterior.getDataVencimento();
                        };
                        novaTarefa.setDataVencimento(novaData);
                }
                repository.save(novaTarefa);
        }
        public Tarefa atualizarStatus(Long id, StatusTarefa novoStatus) {
                Tarefa tarefa = buscarPorId(id);
                tarefa.setStatus(novoStatus);
                if (novoStatus == StatusTarefa.CONCLUIDA) {
                        tarefa.setDataConclusao(LocalDateTime.now());

                        if (tarefa.getRecorrencia() != null && tarefa.getRecorrencia() != Recorrencia.NENHUMA) {
                                gerarProximaTarefaRecorrente(tarefa);
                        }
                }
                return repository.save(tarefa);
        }

        public void deletar(Long id) {
                repository.deleteById(id);
        }

        public long contarPorStatus(StatusTarefa status) {
                return repository.countByStatus(status);
        }

        @org.springframework.scheduling.annotation.Scheduled(cron = "0 0 9 * * *")
        public void verificarLembretesVencimento() {
                LocalDateTime amanha = LocalDateTime.now().plusDays(1);
                List<Tarefa> tarefasUrgentes = repository.findByDataVencimentoBeforeAndStatus(amanha, StatusTarefa.PENDENTE);

                for (Tarefa t: tarefasUrgentes) {
                        System.out.println("LEMBRETE: A tarefa '" + t.getTitulo() + "' vence em breve!");
                }
        }
}
