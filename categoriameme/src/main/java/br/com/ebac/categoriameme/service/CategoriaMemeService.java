package br.com.ebac.categoriameme.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ebac.categoriameme.entity.CategoriaMeme;
import br.com.ebac.categoriameme.repository.CategoriaMemeRepository;

@Component
public class CategoriaMemeService {
    @Autowired
    private final CategoriaMemeRepository categoriaMemeRepository;

    public CategoriaMemeService(CategoriaMemeRepository categoriaMemeRepository) {
        this.categoriaMemeRepository = categoriaMemeRepository;
    }

    public Iterable<CategoriaMeme> encontrarTodasCategorias() {
        return categoriaMemeRepository.findAll();
    }

    public CategoriaMeme criarCategoria(CategoriaMeme categoriaMeme) {
        return categoriaMemeRepository.save(categoriaMeme);
    }

    public CategoriaMeme encontrarCategoriaPorId(Long id) {
        Optional<CategoriaMeme> categoriaOptional = categoriaMemeRepository.findById(id);
        if (categoriaOptional.isPresent()) {
            return categoriaOptional.get();
        } else {
            throw new RuntimeException("Categoria não encontrada");
        }
    }

}
