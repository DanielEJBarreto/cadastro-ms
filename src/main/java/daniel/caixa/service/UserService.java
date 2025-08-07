package daniel.caixa.service;

import daniel.caixa.dto.UserRequest;
import daniel.caixa.dto.UserResponse;
import daniel.caixa.entity.UserRoles;
import daniel.caixa.entity.Usuario;
import daniel.caixa.exception.UserNotFoundException;
import daniel.caixa.mapper.UserMapper;
import daniel.caixa.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

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
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
    }

    @Transactional
    public UserResponse createUser(UserRequest dto){
        Usuario entity = mapper.toEntity(dto);
        repository.persist(entity);
        return mapper.toResponse(entity);
    }

    @Transactional
    public void deleteUser(Long userId){
        Usuario usuario = repository.findByIdOptional(userId)
                .orElseThrow(() -> new UserNotFoundException("Booking not found"));

        usuario.delete();
    }

    public List<UserResponse> listAll() {
        return repository.listAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }
}
