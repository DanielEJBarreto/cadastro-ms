package daniel.caixa.exception;

import io.quarkus.logging.Log;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Exception> {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionMapper.class);

    @Override
    public Response toResponse(Exception e) {

        Log.error(e);

        ErrorResponse error;

        if (e instanceof UserNotFoundException) {
            error = new ErrorResponse("404", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(error).build();
        }

        if (e instanceof UserAlreadyExists) {
            error = new ErrorResponse("409", e.getMessage());
            return Response.status(Response.Status.CONFLICT).entity(error).build();
        }


        // fallback genérico
        error = new ErrorResponse(LocalDateTime.now(), "UNKNOWN ERROR", e.getMessage());


        return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
    }
}
