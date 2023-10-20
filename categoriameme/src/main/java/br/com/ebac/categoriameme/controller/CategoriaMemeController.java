package br.com.ebac.categoriameme.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ebac.categoriameme.client.CategoriaMemeClient;
import br.com.ebac.categoriameme.entity.CategoriaMeme;
import br.com.ebac.categoriameme.entity.Usuario;
import br.com.ebac.categoriameme.service.CategoriaMemeService;

@RestController
@RequestMapping(path = "/categorias-meme")
public class CategoriaMemeController {
    private static final Logger logger = LoggerFactory.getLogger(CategoriaMemeController.class);
    private final CategoriaMemeService categoriaMemeService;

    @Autowired
    private CategoriaMemeClient categoriaMemeClient;

    public CategoriaMemeController(CategoriaMemeService categoriaMemeService) {
        this.categoriaMemeService = categoriaMemeService;
    }

    @GetMapping("/usuarios")
    public Iterable<Usuario> encontrarTodosUsuarios() {
        logger.info("Finding all usuarios");
        Iterable<Usuario> usuarios = categoriaMemeClient.EncontrarTodos();
        int numUsuarios = 0;
        for (Usuario usuario : usuarios) {
            numUsuarios++;
        }
        logger.info("encontrarTodosUsuarios() returned {} usuarios", numUsuarios);
        return usuarios;
    }

    @PostMapping
    public CategoriaMeme criarCategoria(@RequestBody CategoriaMeme categoriaMeme) {
        logger.info("Creating categoria: {}", categoriaMeme);
        Long usuarioId = categoriaMeme.getUsuarioId();
        Usuario usuario = categoriaMemeClient.EncontrarPorId(usuarioId);
        if (usuario == null) {
            logger.info("criarCategoria() failed: usuario with ID {} not found", usuarioId);
            return null;
        }
        categoriaMeme.setUsuarioId(usuario.getId());
        CategoriaMeme categoriaCriada = categoriaMemeService.criarCategoria(categoriaMeme);
        if (categoriaCriada == null) {
            logger.info("criarCategoria() failed: could not create categoriaMeme={}", categoriaMeme);
        } else {
            logger.info("criarCategoria() succeeded: categoriaMeme={} created with ID {}", categoriaCriada,
                    categoriaCriada.getId());
        }
        return categoriaCriada;
    }

    @GetMapping
    public Iterable<CategoriaMeme> encontrarTodasCategorias() {
        logger.info("Finding all categorias");
        Iterable<CategoriaMeme> categorias = categoriaMemeService.encontrarTodasCategorias();
        int numCategorias = 0;
        for (CategoriaMeme categoria : categorias) {
            numCategorias++;
        }
        logger.info("encontrarTodasCategorias() returned {} categorias", numCategorias);
        return categorias;
    }

    @GetMapping("/{id}")
    public CategoriaMeme encontrarCategoriaPorId(@PathVariable Long id) {
        logger.info("Finding categoria with id={}", id);
        CategoriaMeme categoria = categoriaMemeService.encontrarCategoriaPorId(id);
        if (categoria == null) {
            logger.info("encontrarCategoriaPorId() failed: categoria with ID {} not found", id);
        } else {
            logger.info("encontrarCategoriaPorId() succeeded: categoria={} found", categoria);
        }
        return categoria;
    }

}
