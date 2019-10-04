package com.service.main;

import com.google.gson.Gson;
import com.service.main.entities.Autor;
import com.service.main.entities.Libro;
import com.service.main.repository.BookRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author John
 */
@RestController
public class ServerController {

    @Autowired
    @Qualifier("jdbcBookRepository")
    private BookRepository repository;

    @GetMapping("/libros")
    public List<Libro> index() {
        return repository.libros();
    }

    @GetMapping("/autores")
    public List<Autor> autores() {
        return repository.autores();
    }

    @PostMapping("/libros")
    public Libro createOrUpdate(@RequestBody Libro libro) {
        if (libro.getId() == null) {
            repository.craerLibro(libro);
        } else {
            repository.updateLibro(libro);
        }
        Gson gson = new Gson();
        System.out.println(gson.toJson(libro));
        return libro;
    }

    @GetMapping("/libro/{id}")
    public Optional<Libro> libro(@PathVariable("id") Integer id) {
        return repository.getLibro(id);
    }
    
    @DeleteMapping("/libro/{id}")
    public int deleteLibro(@PathVariable("id") Integer id) {
        return repository.deleteLibroById(id);
    }
}
