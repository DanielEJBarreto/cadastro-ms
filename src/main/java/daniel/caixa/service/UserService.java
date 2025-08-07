package daniel.caixa.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import daniel.caixa.dto.UserRequest;
import daniel.caixa.dto.UserResponse;
import daniel.caixa.entity.EmailUtils;
import daniel.caixa.entity.Usuario;
import daniel.caixa.exception.UserAlreadyExists;
import daniel.caixa.exception.UserNotFoundException;
import daniel.caixa.mapper.UserMapper;
import daniel.caixa.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository repository;

    @Inject
    UserMapper mapper;

    public UserResponse findById(Long id) {
        return repository.findByIdOptional(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));
    }

    @Transactional
    public UserResponse createUser(UserRequest dto) {

        // Confere se o usuario já existe
        if (repository.findByUsuario(dto.getUsuario()) != null){
            throw new UserAlreadyExists("User already exists!");
        }

        // Hashear a senha antes de criar a entidade
        String senhaHash = BCrypt.withDefaults()
                .hashToString(12, dto.getSenha().toCharArray());

        // Converte o DTO para entidade
        Usuario entity = mapper.toEntity(dto);
        entity.setSenhaHash(senhaHash);

        // Persiste no banco
        repository.persist(entity);

        // Converte para resposta
        UserResponse response = mapper.toResponse(entity);

        // Mascara o e-mail na resposta
        response.setEmailMasked(EmailUtils.maskEmail(entity.getEmail()));

        return response;
    }

    @Transactional
    public void deleteUser(Long userId){
        Usuario usuario = repository.findByIdOptional(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        usuario.delete();
    }

    public List<UserResponse> listAll() {
        return repository.listAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }
}
