package com.usuario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usuario.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	boolean existsByUsername(String usarname); 
	
	// para autenticar usuario
	Optional<Usuario> findByUsername(String Username);
}
