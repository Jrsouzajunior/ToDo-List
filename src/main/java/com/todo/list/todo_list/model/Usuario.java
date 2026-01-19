package com.todo.list.todo_list.model;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Usuario {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        
        @NotBlank(message = "O nome é obrigatório")
        private String nome;

        @Column(unique = true, nullable = false)
        @Email(message = "E-mail inválido")
        private String email;
        
        private String senha;

        @Column(length = 11, unique = true)
        @CPF(message = "CPF inválido")
        private String cpf;

        private String rg;

        @Past(message = "Data de nascimento inválida")
        private LocalDate dataNascimento;

        private String cep;
        private String endereco;
        private String cidade;
        private String estado;
        private String telefone;

        @OneToMany(mappedBy = "usuario")
        private List<Tarefa> tarefas;
}
