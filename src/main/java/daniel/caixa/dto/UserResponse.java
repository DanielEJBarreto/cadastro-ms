package daniel.caixa.dto;

import daniel.caixa.entity.UserRoles;

public class UserResponse {
//ATRIBUTOS
    private Long id;
    private String usuario;
    private String senha;
    private String nome;
    private String emailMasked;
    private UserRoles roles;

    public UserResponse(){}
    public UserResponse(Long id, String usuárioCriadoComSucesso) {}

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

    public String getEmailMasked() {
        return emailMasked;
    }

    public void setEmailMasked(String emailMasked) {
        this.emailMasked = emailMasked;
    }

    public UserRoles getRoles() {
        return roles;
    }

    public void setRoles(UserRoles roles) {
        this.roles = roles;
    }
}
