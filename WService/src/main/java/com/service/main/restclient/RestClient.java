package com.service.main.restclient;

import com.service.main.entities.Autor;
import com.service.main.entities.Libro;
import java.util.List;

/**
 *
 * @author John
 */
public interface RestClient {

    List<Autor> autores();

    List<Libro> libros();
    
    void guardarLibro(Libro libro);
    
    Libro getLibroById(Integer id);
    
    void eliminarLibro(Libro libro);
}
