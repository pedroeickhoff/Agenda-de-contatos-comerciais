package nuvem.projeto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
class UnitariosTest {

    @Mock
    private ContatosRepository repository;

    @InjectMocks
    private ContatosController controller;

    @Test
    @DisplayName("Unit 1: Testar Getter e Setter do Nome no Modelo")
    void testModeloNome() {
        Contatos c = new Contatos();
        c.setNome("Teste Unitario");
        assertEquals("Teste Unitario", c.getNome());
    }

    @Test
    @DisplayName("Unit 2: Testar Getter e Setter da Empresa")
    void testModeloEmpresa() {
        Contatos c = new Contatos();
        c.setEmpresa("PUC");
        assertEquals("PUC", c.getEmpresa());
    }

    @Test
    @DisplayName("Unit 3: Testar Getter e Setter do Email")
    void testModeloEmail() {
        Contatos c = new Contatos();
        c.setEmail("teste@puc.br");
        assertEquals("teste@puc.br", c.getEmail());
    }

    @Test
    @DisplayName("Unit 4: Mockar retorno de lista vazia no Controller")
    void testControllerGetVazio() {
        when(repository.findAll()).thenReturn(Arrays.asList());
        assertEquals(0, controller.getContatos().size());
    }

    @Test
    @DisplayName("Unit 5: Mockar salvamento no Controller")
    void testControllerSalvar() {
        Contatos c = new Contatos();
        c.setNome("Novo");
        // Apenas verifica se não lança erro, já que o método retorna void
        controller.addContato(c); 
    }
    
    @Test
    @DisplayName("Unit 6: Mockar deleção no Controller")
    void testControllerDeletar() {
        // Verifica execução sem erro
        controller.deletar(1);
    }
    
    @Test
    @DisplayName("Unit 7: Testar Setter de Telefone")
    void testModeloTelefone() {
        Contatos c = new Contatos();
        c.setTelefone("199999999");
        assertEquals("199999999", c.getTelefone());
    }
}

@SpringBootTest
@AutoConfigureMockMvc
class IntegracaoTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Integration 1: Deve criar um contato via API (POST)")
    void testCriarContato() throws Exception {
        Contatos contato = new Contatos();
        contato.setNome("Contato Integracao");
        contato.setEmail("integracao@teste.com");
        contato.setEmpresa("Test Corp");
        
        mockMvc.perform(post("/cadastro/cadastrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(contato)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Integration 2: Deve listar contatos (GET)")
    void testListarContatos() throws Exception {
        mockMvc.perform(get("/contatos/ver"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Integration 3: Tentar deletar um ID inexistente (DELETE)")
    void testDeletar() throws Exception {
        mockMvc.perform(delete("/contatos/ver/999"))
                .andExpect(status().isOk());
    }
}