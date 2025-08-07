package daniel.caixa.mapper;

import daniel.caixa.dto.UserRequest;
import daniel.caixa.dto.UserResponse;
import daniel.caixa.entity.EmailUtils;
import daniel.caixa.entity.Usuario;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserMapper {

    public Usuario toEntity(UserRequest dto) {
        Usuario b = new Usuario();
        b.setId(dto.getId());
        b.setUsuario(dto.getUsuario());
        b.setSenhaHash(dto.getSenha());
        b.setNome(dto.getNome());
        b.setEmail(dto.getEmail());
        b.setRoles(dto.getRoles());
        return b;
    }

    public UserResponse toResponse(Usuario b) {
        UserResponse dto = new UserResponse();
        dto.setId(b.getId());
        dto.setUsuario(b.getUsuario());
        dto.setSenha(b.getSenhaHash());
        dto.setNome(b.getNome());
        dto.setEmailMasked(EmailUtils.maskEmail(b.getEmail()));
        dto.setRoles(b.getRoles());
        return dto;
    }
}
