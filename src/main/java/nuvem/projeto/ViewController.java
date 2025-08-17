package nuvem.projeto;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String index() {
        return "home"; // templates/home.html
    }

    @GetMapping("/cadastro.html")
    public String cadastro() {
        return "cadastro"; // templates/cadastro.html
    }

    @GetMapping("/contatos.html")
    public String contato() {
        return "contatos"; // templates/contato.html
    }
}
