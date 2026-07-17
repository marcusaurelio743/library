package libraryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import libraryapi.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

}
