package com.service.main.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author John
 */
public class Autor {

    private Integer id;
    private String nombre;
    @JsonIgnore
    private Boolean selected;

    public Autor(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Autor() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

}
