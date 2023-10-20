package br.com.ebac.usuario.service;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.ebac.usuario.entity.Usuario;
import br.com.ebac.usuario.repository.UsuarioRepository;

@Component
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Iterable<Usuario> encontrarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario criarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario encontrarPorId(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            return usuarioOptional.get();
        } else {
            throw new RuntimeException("Usuário não encontrado");
        }
    }
}
