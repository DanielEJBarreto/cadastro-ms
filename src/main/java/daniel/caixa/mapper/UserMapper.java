package daniel.caixa.mapper;

import daniel.caixa.dto.UserRequest;
import daniel.caixa.dto.UserResponse;
import daniel.caixa.entity.Usuario;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserMapper {

    public Usuario toEntity(UserRequest dto) {
        Usuario b = new Usuario();
        b.setId(dto.getId());
        b.setUsuario(dto.getUsuario());
        b.setSenha(dto.getSenha());
        b.setNome(dto.getNome());
        b.setEmail(dto.getEmail());
        b.setRoles(dto.getRoles());
        return b;
    }

    public UserResponse toResponse(Usuario b) {
        UserResponse dto = new UserResponse();
        dto.setId(b.getId());
        dto.setUsuario(b.getUsuario());
        dto.setSenha(b.getSenha());
        dto.setNome(b.getNome());
        dto.setEmail(b.getEmail());
        dto.setRoles(b.getRoles());
        return dto;
    }
}
