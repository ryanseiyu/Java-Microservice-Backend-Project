package br.com.ebac.usuario.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.ebac.usuario.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

}
