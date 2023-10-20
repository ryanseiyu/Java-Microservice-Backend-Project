package br.com.ebac.meme.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.ebac.meme.entity.Meme;

public interface MemelandiaRepository extends CrudRepository<Meme, Long> {

}
