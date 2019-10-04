package com.service.main.repository;

import com.service.main.entities.Autor;
import com.service.main.entities.Libro;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author John
 */
public interface BookRepository {

    int count();

    int craerLibro(Libro libro);

    int updateLibro(Libro libro);

    int deleteLibroById(Integer id);

    List<Libro> libros();

    Optional<Libro> getLibro(Integer id);

    String getNameById(Long id);
    
    List<Autor> autores();

}
