package nuvem.projeto;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/home.html")
    public String index() {
        return "home"; 
    }

    @GetMapping("/cadastro.html")
    public String cadastro() {
        return "cadastro"; 
    }

    @GetMapping("/contatos.html")
    public String contato() {
        return "contatos"; 
    }

    @GetMapping("/editar.html")
    public String editar() {
        return "editar"; 
    }

}
