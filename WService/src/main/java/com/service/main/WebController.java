package com.service.main;

import com.google.gson.Gson;
import com.service.main.entities.Autor;
import com.service.main.entities.Libro;
import com.service.main.restclient.RestClient;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * WebController RestClient UI
 *
 * @author John
 */
@Controller
public class WebController {

    @Autowired
    RestClient restClient;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("libros", restClient.libros());

        return "index";
    }

    @RequestMapping(value = "/nuevo", method = RequestMethod.GET)
    public String nuevo(Model model) {

        model.addAttribute("autores", restClient.autores());

        return "nuevo";
    }

    @RequestMapping(value = "/nuevo", method = RequestMethod.POST)
    public String gradarLibro(Libro libro) {

        Gson gson = new Gson();
        System.out.println(gson.toJson(libro));

        restClient.guardarLibro(libro);

        return "redirect:index";
    }

    @RequestMapping(value = "/getlibro", method = RequestMethod.GET)
    public String getlibro(Model model, @RequestParam Integer id) {

        Libro libro = restClient.getLibroById(id);
        model.addAttribute("libro", libro);

        List<Autor> autores = restClient.autores();
        autores.forEach((a) -> {
            for (Integer aid : libro.getIds()) {
                if (a.getId().equals(aid)) {
                    a.setSelected(Boolean.TRUE);
                }
            }
        });

        model.addAttribute("autores", autores);

        return "update";
    }

    @RequestMapping(value = "/updatelibro", method = RequestMethod.POST)
    public String updateLibro(Libro libro) {

        Gson gson = new Gson();
        System.out.println(gson.toJson(libro));

        restClient.guardarLibro(libro);

        return "redirect:index";
    }

    @RequestMapping(value = "/eliminarlibro", method = RequestMethod.GET)
    public String eliminarLibro(Model model, @RequestParam Integer id) {

        Libro libro = restClient.getLibroById(id);
        model.addAttribute("libro", libro);

        restClient.eliminarLibro(libro);

        return "deletedMessage";
    }
}
