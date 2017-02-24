package com.sldg.erp.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import com.sldg.erp.model.Usuario;
import com.sldg.erp.repository.Usuarios;
import com.sldg.erp.util.Transacional;

public class UsuarioService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Usuarios usuarios;
	
	@Transacional
	public void salvar(Usuario usuario) {
		usuarios.guardar(usuario);
	}
	
	public void autenticar(Usuario usuario) {
		if ("".equals(usuario.getSenha()) || "".equals(usuario.getEmail())) {
			throw new RuntimeException("Usuário ou senhas não informado");
		} else {
			usuarios.autentica(usuario);
		}
	}
	
}
