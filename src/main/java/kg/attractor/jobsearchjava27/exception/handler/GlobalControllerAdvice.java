package kg.attractor.jobsearchjava27.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.jobsearchjava27.exception.NotFoundEntryException;
import kg.attractor.jobsearchjava27.service.ErrorService;
import lombok.RequiredArgsConstructor;
import org.h2.engine.Mode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.stream.Collectors;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {
    private final ErrorService errorService;

    @ExceptionHandler(NotFoundEntryException.class)
    private String noSuchFileExceptionHandler(HttpServletRequest request , Model model, NotFoundEntryException e) {
        model.addAttribute("status", HttpStatus.NOT_FOUND.value());
        model.addAttribute("reason", HttpStatus.NOT_FOUND.getReasonPhrase() + ": " + e.getMessage());
        model.addAttribute("details", request);
        return "errors/error";
    }

    @ExceptionHandler(SQLException.class)
    private String sqlExceptionHandler(HttpServletRequest request ,Model model, SQLException e) {
        model.addAttribute("status", HttpStatus.BAD_REQUEST.value());
        model.addAttribute("reason", HttpStatus.BAD_REQUEST.getReasonPhrase() + ": " + e.getMessage());
        model.addAttribute("details", request);
        return "errors/error";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private String validationHandler(HttpServletRequest request ,Model model ,MethodArgumentNotValidException e) {
        String errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
                .collect(Collectors.joining(", "));
        model.addAttribute("status", HttpStatus.BAD_REQUEST.value());
        model.addAttribute("reason", "Validation failed: " + errors);
        model.addAttribute("details", request);
        return "errors/error";
    }
}
