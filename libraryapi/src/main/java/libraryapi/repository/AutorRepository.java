package libraryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import libraryapi.model.Autor;

public interface AutorRepository extends JpaRepository<Autor,Long > {

}
