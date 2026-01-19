package com.todo.list.todo_list.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "subtarefas")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Subtarefa {
        
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank(message = "O título é obrigatório")
        private String titulo;

        private boolean concluida = false;

        @ManyToOne
        @JoinColumn(name = "id_tarefa")
        private Tarefa tarefa;
}
