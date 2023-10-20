package br.com.ebac.meme.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.ebac.meme.client.MemeClient;
import br.com.ebac.meme.entity.CategoriaMeme;
import br.com.ebac.meme.entity.Meme;
import br.com.ebac.meme.entity.Usuario;
import br.com.ebac.meme.service.MemelandiaService;

@RestController
@RequestMapping(path = "/memelandia")
public class MemelandiaController {
    private static final Logger logger = LoggerFactory.getLogger(MemelandiaController.class);
    private final MemelandiaService memelandiaService;

    @Autowired
    private MemeClient memeClient;

    public MemelandiaController(MemelandiaService memelandiaService) {
        this.memelandiaService = memelandiaService;
    }

    @GetMapping
    public Iterable<Meme> encontrarTodosMemes() {
        logger.info("Finding all memes");
        Iterable<Meme> memes = memelandiaService.encontrarTodosMemes();
        long count = StreamSupport.stream(memes.spliterator(), false).count();
        logger.info("Found {} memes", count);
        return memes;
    }

    @PostMapping
    public Meme criarMeme(@RequestBody Meme meme) {
        logger.info("Creating meme: {}", meme);
        Long usuarioId = meme.getUsuarioId();
        Long categoriaMemeId = meme.getCategoriaMemeId();
        Usuario usuario = memeClient.EncontrarUsuarioPorId(usuarioId);
        CategoriaMeme categoriaMeme = memeClient.EncontrarCategoriaPorID(categoriaMemeId);
        if (usuario == null || categoriaMeme == null) {
            logger.warn("Usuario or categoria not found for meme: {}", meme);
            return null;
        }
        meme.setUsuarioId(usuario.getId());
        meme.setCategoriaMemeId(categoriaMeme.getId());
        Meme novoMeme = memelandiaService.criarMeme(meme);
        logger.info("Meme created successfully: {}", novoMeme);
        return novoMeme;
    }

    @GetMapping("/categorias-meme")
    public Iterable<CategoriaMeme> encontrarTodasCategorias() {
        logger.info("Finding all categorias");
        Iterable<CategoriaMeme> categorias = memeClient.EncontrarTodasCategorias();
        long count = StreamSupport.stream(categorias.spliterator(), false).count();
        logger.info("Found {} categorias from meme", count);
        return categorias;
    }

    @GetMapping("/usuarios")
    public Iterable<Usuario> encontrarTodosUsuarios() {
        logger.info("Finding all usuarios");
        Iterable<Usuario> usuarios = memeClient.EncontrarTodosUsuarios();
        long count = StreamSupport.stream(usuarios.spliterator(), false).count();
        logger.info("Found {} usuarios", count);
        return usuarios;
    }

    @GetMapping("/meme-do-dia")
    public Meme encontrarMemeDoDia() {
        logger.info("Finding meme of the day");
        List<Meme> todosOsMemes = StreamSupport.stream(memelandiaService.encontrarTodosMemes().spliterator(), false)
                .collect(Collectors.toList());
        if (todosOsMemes.isEmpty()) {
            logger.warn("None meme found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No meme was found in the database");
        }
        Collections.shuffle(todosOsMemes);
        Meme memeDoDia = todosOsMemes.get(0);
        logger.info("Meme found: {}", memeDoDia);
        return memeDoDia;
    }
}
