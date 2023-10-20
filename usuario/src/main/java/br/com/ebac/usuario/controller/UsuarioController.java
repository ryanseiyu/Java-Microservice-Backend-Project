package br.com.ebac.usuario.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ebac.usuario.entity.Usuario;
import br.com.ebac.usuario.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        logger.info("Creating usuario: {}", usuario);
        Usuario createdUsuario = usuarioService.criarUsuario(usuario);
        logger.info("Usuario created: {}", createdUsuario);
        return createdUsuario;
    }

    @GetMapping
    public Iterable<Usuario> encontrarTodos() {
        logger.info("Finding all usuarios");
        Iterable<Usuario> usuarios = usuarioService.encontrarTodos();
        logger.info("Found {} usuarios", usuarios.spliterator().getExactSizeIfKnown());
        return usuarios;
    }

    @GetMapping("/{id}")
    public Usuario encontrarPorId(@PathVariable Long id) {
        logger.info("Finding usuario with id: {}", id);
        Usuario usuario = usuarioService.encontrarPorId(id);
        logger.info("Found usuario: {}", usuario);
        return usuario;
    }
}
