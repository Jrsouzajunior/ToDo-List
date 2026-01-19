package com.todo.list.todo_list.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo.list.todo_list.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
        Optional<Usuario> findByEmail(String email);
        Optional<Usuario> findByCpf(String cpf);

        boolean existsByCpf(String cpf);
}
