package nuvem.projeto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Contatos")
public class Contatos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;
    private String cep;
    private String empresa;
    private String rua;
    private String bairro;
    private String numero;
    private String email;
    private String cargo;

    public Contatos() {}

    public Contatos(String nome, String email, String cep, String empresa, String rua, String bairro, String numero, String cargo) {
        this.nome = nome;
        this.email = email;
        this.cep = cep;
        this.empresa = empresa;
        this.rua = rua;
        this.bairro = bairro;
        this.numero = numero;
        this.cargo = cargo;
    }


    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }

    public String getEmpresa() { return empresa; }
    public void setEmpresa(String empresa) { this.empresa = empresa; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getId() {
        return id;
    }

}
