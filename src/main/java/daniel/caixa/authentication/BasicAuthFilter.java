package daniel.caixa.authentication;

import daniel.caixa.repository.UserRepository;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import daniel.caixa.entity.Usuario;
import at.favre.lib.crypto.bcrypt.BCrypt;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class BasicAuthFilter implements ContainerRequestFilter {

    @Inject
    UserRepository repository;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String path = requestContext.getUriInfo().getPath();

        // Ignora rotas públicas
        if (path.startsWith("/usuario") || path.startsWith("public")) {
            return;
        }
        String authHeader = requestContext.getHeaderString("Authorization");

        if (authHeader == null || !authHeader.startsWith("Basic ")) {
            abort(requestContext, "Missing or invalid Authorization header");
            return;
        }

        String base64Credentials = authHeader.substring("Basic ".length()).trim();
        String credentials = new String(Base64.getDecoder().decode(base64Credentials), StandardCharsets.UTF_8);

        String[] values = credentials.split(":", 2);
        if (values.length != 2) {
            abort(requestContext, "Invalid credentials format");
            return;
        }

        String username = values[0];
        String password = values[1];

        Usuario user = repository.findByUsuario(username);
        if (user == null || !BCrypt.verifyer().verify(password.toCharArray(), user.getSenhaHash()).verified) {
            abort(requestContext, "Invalid username or password");
        }
    }

    private void abort(ContainerRequestContext context, String message) {
        context.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                .entity("Unauthorized: " + message)
                .build());
    }
}
