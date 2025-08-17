package nuvem.projeto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class ContatosController {

    @Autowired
    private ContatosRepository repository;

    @PostMapping("/cadastro/cadastrar")
    public void addContato(@RequestBody Contatos contato) {
        repository.save(contato);
    }

    @GetMapping("/contatos/ver")
    public List<Contatos> getContatos() {
        return repository.findAll();
    }

    @DeleteMapping("/contatos/ver/{id}")
    public void deletar(@PathVariable Integer id) {
        repository.deleteById(id);
    }

    @PutMapping("/contatos/ver/{id}")
    public ResponseEntity<Contatos> atualizar(@PathVariable Integer id, @RequestBody Contatos atualizado) {
    return repository.findById(id)
        .map(contato -> {
            contato.setNome(atualizado.getNome());
            contato.setEmail(atualizado.getEmail());
            contato.setCep(atualizado.getCep());
            contato.setEmpresa(atualizado.getEmpresa());
            contato.setCargo(atualizado.getCargo());
            contato.setRua(atualizado.getRua());
            contato.setBairro(atualizado.getBairro());
            contato.setNumero(atualizado.getNumero());
            repository.save(contato);
            return ResponseEntity.ok(contato);
        })
        .orElse(ResponseEntity.notFound().build());
}


}

