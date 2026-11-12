
package com.usuario.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.usuario.config.JwtUtils;
import com.usuario.modelo.Usuario;
import com.usuario.repository.UsuarioRepository;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils; 
    
    public Usuario registroUsuario(Usuario usuario) {

        if (usuarioRepository.existsByUsername(usuario.getUsername())) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "El usuario '" + usuario.getUsername() +
                "' ya existe en el sistema"
            );
        }

        usuario.setPassword(
            passwordEncoder.encode(usuario.getPassword())
        );

        return usuarioRepository.save(usuario);
    }
    
    
    //para autenticar usuarioi
    public String autentificarUsuario(String username, String password) {
    	
    	Usuario usuario = usuarioRepository.findByUsername(username)
    			.orElseThrow(() -> new ResponseStatusException(
    		HttpStatus.UNAUTHORIZED, "Usuario O contrasena incorreto"
    					));
    	
    	if (!passwordEncoder.matches(password, usuario.getPassword())) {
    		throw new ResponseStatusException(
    				HttpStatus.UNAUTHORIZED, "Usuario O contrasena incorreto"
    				);
		}
    	
    	return jwtUtils.generarToken(usuario.getUsername(), usuario.getPassword());
    	
    }
    
}
