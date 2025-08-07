package daniel.caixa.entity;

public enum UserRoles {

    USUARIO,
    FUNCIONARIO,
    ADMIN

}
//* Eu, como usuário posso ter roles (`USUARIO`, `ADMIN`, `FUNCIONARIO`).
//  * `USUARIO` só pode realizar leitura dos veículos;
//  * `FUNCIONARIO` pode alterar status e buscar;
//  * `ADMIN` pode fazer tudo; -->