package daniel.caixa.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.smallrye.common.constraint.NotNull;
import jakarta.persistence.*;

@Entity
public class Usuario extends PanacheEntityBase {
//ATRIBUTOS

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Id
    private Long id;

    @NotNull
    private String usuario;

    @NotNull
    private String senha;

    @NotNull
    private String nome;

    @NotNull
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRoles roles;

//GETTERS E SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRoles getRoles() {
        return roles;
    }

    public void setRoles(UserRoles roles) {
        this.roles = roles;
    }
}
