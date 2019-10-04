package com.service.main.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author John
 */
public class Libro {

    private Integer id;
    private String titulo;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date fecha_edicion;
    // Autor ids and size
    private Set<Integer> ids = new HashSet<>();
    private Integer size;    

    public Integer getSize() {
        if (size == null) {
            return ids.size();
        }
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Libro(Integer id, String titulo, Date fecha_edicion) {
        this.id = id;
        this.titulo = titulo;
        this.fecha_edicion = fecha_edicion;
    }

    public Libro() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFecha_edicion() {
        return fecha_edicion;
    }

    public void setFecha_edicion(Date fecha_edicion) {
        this.fecha_edicion = fecha_edicion;
    }

    public Set<Integer> getIds() {
        return ids;
    }

    public void setIds(Set<Integer> ids) {
        this.ids = ids;
    }

    public void addIds(Integer i) {
        if (ids != null) {
            ids = new HashSet<>();
        }
        ids.add(i);
    }

}
