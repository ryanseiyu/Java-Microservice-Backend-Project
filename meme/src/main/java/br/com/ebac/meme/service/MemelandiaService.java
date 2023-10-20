package br.com.ebac.meme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ebac.meme.entity.Meme;
import br.com.ebac.meme.repository.MemelandiaRepository;

@Component
public class MemelandiaService {
    @Autowired
    private final MemelandiaRepository memelandiaRepository;

    public MemelandiaService(MemelandiaRepository memelandiaRepository) {
        this.memelandiaRepository = memelandiaRepository;
    }

    public Meme criarMeme(Meme meme) {
        return memelandiaRepository.save(meme);
    }

    public Iterable<Meme> encontrarTodosMemes() {
        return memelandiaRepository.findAll();
    }
}
