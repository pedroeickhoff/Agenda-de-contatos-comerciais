package nuvem.projeto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*") // Permite chamadas do frontend
public class ContatosController {

    private List<Contatos> listaContatos = new ArrayList<>();

    @PostMapping("/contatos")
    public void addContato(@RequestBody Contatos contato) {
        listaContatos.add(contato);
    }

    @GetMapping("/contatos")
    public List<Contatos> getContatos() {
        return listaContatos;
    }
}
