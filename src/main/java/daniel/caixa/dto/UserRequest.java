
package daniel.caixa.dto;

import daniel.caixa.entity.UserRoles;
import io.smallrye.common.constraint.NotNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserRequest {
//ATRIBUTOS
    @NotBlank
    private String usuario;

    @NotNull
    private String senha;

    @NotNull
    private Long id;

    @NotNull
    private String nome;

    @NotBlank
    @Email
    private String email;

    private UserRoles roles = UserRoles.USUARIO;

//GETTERS E SETTERS

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setEmailMasked(String email) {
        this.email = email;
    }

    public UserRoles getRoles() {
        return roles;
    }

    public void setRoles(UserRoles roles) {
        this.roles = roles;
    }
}
