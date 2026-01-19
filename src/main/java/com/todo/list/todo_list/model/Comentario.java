package com.todo.list.todo_list.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comentarios")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Comentario {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank(message = "O texto do comentário não pode estar vazio")
        @Column(columnDefinition = "TEXT")
        private String texto;

        private LocalDateTime dataCriacao = LocalDateTime.now();

        @ManyToOne
        @JoinColumn(name = "id_tarefa")
        @JsonBackReference
        private Tarefa tarefa;
}
