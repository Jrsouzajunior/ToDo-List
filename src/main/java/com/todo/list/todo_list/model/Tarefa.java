package com.todo.list.todo_list.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tarefas")
@Getter
@Setter

public class Tarefa {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        
        @Column(nullable = false)
        private String titulo;

        private String descricao;

        @Enumerated(EnumType.STRING)
        @Column
        private Recorrencia recorrencia = Recorrencia.NENHUMA;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private StatusTarefa status;

        @Column(nullable = false)
        private LocalDateTime dataCriacao;

        private LocalDateTime dataConclusao;

        private LocalDateTime dataVencimento;

        @Column(nullable = false)
        private Integer prioridade; // 1-5

        @PrePersist
        protected void onCreate() {
                this.dataCriacao = LocalDateTime.now();
                if (this.status == null) {
                        this.status = StatusTarefa.PENDENTE;
                }
        }

        @ManyToOne
        @JoinColumn(name = "id_usuario")
        private Usuario usuario;

        @OneToMany(mappedBy = "tarefa", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Subtarefa> subtarefas;

        @OneToMany(mappedBy = "tarefa", cascade = CascadeType.ALL)
        @com.fasterxml.jackson.annotation.JsonManagedReference
        private List<Comentario> comentarios;
}
