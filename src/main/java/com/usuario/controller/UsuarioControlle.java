package com.usuario.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuario.dto.UsuarioDTO;
import com.usuario.modelo.Usuario;
import com.usuario.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3000)
@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioControlle {
	private final UsuarioService usuarioService;
	
	@PostMapping("/registrar")
	public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario usuario){
		Usuario nuevoUsuario  = usuarioService.registroUsuario(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
	}
	
	//autenficar usuario
	@PostMapping("/login")
	public ResponseEntity<String> aunteifcarUsuario(
			@RequestBody UsuarioDTO usuarioDTO){
		String Response =
		usuarioService.autentificarUsuario(usuarioDTO.getUsername(), 
				usuarioDTO.getPassword());
		return ResponseEntity.ok(Response);
	}
}
