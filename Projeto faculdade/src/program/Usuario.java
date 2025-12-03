package program;

import java.util.Objects;

public class Usuario {
    private int id;
    private String nome;
    public Usuario(){

    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMoedas(int moedas) {
        this.moedas = moedas;
    }

    public void setDepartamentoId(int departamentoId) {
        this.departamentoId = departamentoId;
    }

    private String email;
    private int moedas;
    private int departamentoId;

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", moedas=" + moedas +
                ", departamentoId=" + departamentoId +
                '}';
    }

    ;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(nome, usuario.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nome);
    }

    public Usuario(int id, String nome, String email, int moedas, int departamentoId) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.moedas = moedas;
        this.departamentoId = departamentoId;
    }

    // Getters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public int getMoedas() { return moedas; }
    public int getDepartamentoId() { return departamentoId; }

}
