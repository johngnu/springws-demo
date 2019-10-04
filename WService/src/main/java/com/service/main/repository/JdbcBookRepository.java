package com.service.main.repository;

import com.service.main.entities.Autor;
import com.service.main.entities.Libro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/**
 *
 * @author John
 */
@Repository
public class JdbcBookRepository implements BookRepository {

    // Spring Boot will create and configure DataSource and JdbcTemplate
    // To use it, just @Autowired
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from libro", Integer.class);
    }

    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }

    @Override
    public int craerLibro(Libro libro) {
        final PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
                final PreparedStatement ps = connection.prepareStatement("insert into libro (titulo, fecha_edicion) values(?,?)",
                        Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, libro.getTitulo());
                ps.setDate(2, convertUtilToSql(libro.getFecha_edicion()));
                return ps;
            }
        };

        KeyHolder keyHolder = new GeneratedKeyHolder();
        /*
        int i = jdbcTemplate.update(
                "insert into libro (titulo, fecha_edicion) values(?,?)",
                libro.getTitulo(), libro.getFecha_edicion());
         */
        int i = jdbcTemplate.update(psc, keyHolder);
        System.out.println("key : " + keyHolder.getKeys().get("id_libro"));

        if (libro.getIds() != null) {
            for (Integer a : libro.getIds()) {
                jdbcTemplate.update(
                        "insert into autor_libro(id_autor, id_libro) values(?, ?)",
                        a, keyHolder.getKeys().get("id_libro"));
            }
        }

        return i;
    }

    @Override
    public int updateLibro(Libro libro) {

        // delete autores
        jdbcTemplate.update("delete from autor_libro where id_libro = ?",
                libro.getId());

        int i = jdbcTemplate.update(
                "update libro set titulo = ?, fecha_edicion = ? where id_libro = ?",
                libro.getTitulo(), libro.getFecha_edicion(), libro.getId());

        // insert new autores
        if (libro.getIds() != null) {
            for (Integer a : libro.getIds()) {
                jdbcTemplate.update(
                        "insert into autor_libro(id_autor, id_libro) values(?, ?)",
                        a, libro.getId());
            }
        }

        return i;
    }

    @Override
    public int deleteLibroById(Integer id) {
        jdbcTemplate.update(
                "delete from autor_libro where id_libro = ?",
                id);

        int i = jdbcTemplate.update(
                "delete from libro where id_libro = ?",
                id);
        return i;
    }

    @Override
    public List<Libro> libros() {
        List<Libro> libros = jdbcTemplate.query(
                "select * from libro",
                (rs, rowNum)
                -> new Libro(
                        rs.getInt("id_libro"),
                        rs.getString("titulo"),
                        rs.getDate("fecha_edicion")
                )
        );
        for (Libro l : libros) {
            Integer size = jdbcTemplate.queryForObject("select count(*) from autor_libro where id_libro = " + l.getId(), Integer.class);
            System.out.println("size : " + size);
            l.setSize(size);
        }

        return libros;
    }

    // jdbcTemplate.queryForObject, populates a single object
    @Override
    public Optional<Libro> getLibro(Integer id) {
        Optional<Libro> ol = jdbcTemplate.queryForObject(
                "select * from libro where id_libro = ?",
                new Object[]{id},
                (rs, rowNum)
                -> Optional.of(new Libro(
                        rs.getInt("id_libro"),
                        rs.getString("titulo"),
                        rs.getDate("fecha_edicion")
                ))
        );
        ol.get().setIds(new HashSet<>(this.libroAutores(ol.get().getId())));
        return ol;
    }

    @Override
    public String getNameById(Long id) {
        return jdbcTemplate.queryForObject(
                "select name from books where id = ?",
                new Object[]{id},
                String.class
        );
    }

    @Override
    public List<Autor> autores() {
        return jdbcTemplate.query(
                "select * from autor",
                (rs, rowNum)
                -> new Autor(
                        rs.getInt("id_autor"),
                        rs.getString("nombre")
                )
        );
    }

    /**
     * Get autor ids for libro
     *
     * @param id
     * @return
     */
    public List<Integer> libroAutores(Integer id) {
        return jdbcTemplate.query(
                "select id_autor from autor_libro where id_libro = ?",
                new Object[]{id},
                (rs, rowNum)
                -> rs.getInt("id_autor")
        );
    }

}
