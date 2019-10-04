package com.service.main.restclient;

import com.service.main.entities.Autor;
import com.service.main.entities.Libro;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author John
 */
@Service
public class RestClientImpl implements RestClient {

    final private String host = "http://localhost:8080/service";

    @Override
    public List<Autor> autores() {
        String theUrl = host + "/autores";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Autor>> response = restTemplate.exchange(
                theUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Autor>>() {
        });
        List<Autor> result = response.getBody();
        return result;
    }

    @Override
    public List<Libro> libros() {
        String theUrl = host + "/libros";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Libro>> response = restTemplate.exchange(
                theUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Libro>>() {
        });
        List<Libro> result = response.getBody();

        return result;
    }

    @Override
    public void guardarLibro(Libro libro) {
        String theUrl = host + "/libros";

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(theUrl, libro, String.class);
    }

    @Override
    public Libro getLibroById(Integer id) {
        String theUrl = host + "/libro/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Libro> response = restTemplate.exchange(
                theUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Libro>() {
        });
        return response.getBody();
    }

    @Override
    public void eliminarLibro(Libro libro) {
        String theUrl = host + "/libro/{id}";
        
        Map<String, Object> params = new HashMap<>();
        params.put("id", libro.getId());
        
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(theUrl, params);
    }

}
