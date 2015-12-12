package com.sldg.erp.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.sldg.erp.model.Usuario;

public class Usuarios implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	/**
	 * 
	 * Metodo que retorna o codigo do usuario
	 * @param codigo
	 * @return
	 */
	public Usuario porCodigo(Long codigo) {
		return manager.find(Usuario.class, codigo);
	}
	
	public Usuario autenticaUsuario(Usuario usuario) {
		Query query = manager.createQuery("from Usuario u where u.email = :pemail and u.codigo = :pcondigo");
		query.setParameter("pemail", usuario.getEmail());
		query.setParameter("pcodigo", usuario.getSenha());
		return (Usuario) query.getResultList();
	}

	public Usuario guardar(Usuario usuario) {
		return manager.merge(usuario);
	}
	
	public List<Usuario> listaUsuario() {
		Query query = manager.createQuery("from Usuario", Usuario.class);
		return query.getResultList();
	}
	
	public List<Usuario> pesquisaUsuarios(Usuario usuario) {

		Query query = manager.createQuery("from Usuario where email = :pemail or codigo = :pcodigo");

		query.setParameter("pemail", usuario.getEmail());
		query.setParameter("pcodigo", usuario.getCodigo());
		List<Usuario> usuarios = query.getResultList();
		
		for (Usuario usuario2 : usuarios) {
			System.out.println(usuario2.getEmail());
			System.out.println(usuario2.getCodigo());
		}
		return usuarios;
	}

}



