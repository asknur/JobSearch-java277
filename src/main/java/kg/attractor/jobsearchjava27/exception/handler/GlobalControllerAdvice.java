package kg.attractor.jobsearchjava27.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.jobsearchjava27.exception.*;
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
    public String notFoundHandler(HttpServletRequest request, Model model, NotFoundEntryException e) {
        model.addAttribute("status", HttpStatus.NOT_FOUND.value());
        model.addAttribute("reason", HttpStatus.NOT_FOUND.getReasonPhrase() + ": " + e.getMessage());
        model.addAttribute("details", request);
        return "errors/error";
    }

    @ExceptionHandler(VacancyNotFoundException.class)
    public String vacancyNotFoundHandler(HttpServletRequest request, Model model, VacancyNotFoundException e) {
        model.addAttribute("status", HttpStatus.NOT_FOUND.value());
        model.addAttribute("reason", "Вакансия не найдена");
        model.addAttribute("details", request);
        return "errors/error";
    }

    @ExceptionHandler(ResumeNotFoundException.class)
    public String resumeNotFoundHandler(HttpServletRequest request, Model model, ResumeNotFoundException e) {
        model.addAttribute("status", HttpStatus.NOT_FOUND.value());
        model.addAttribute("reason", "Резюме не найдено");
        model.addAttribute("details", request);
        return "errors/error";
    }

    @ExceptionHandler(UserNotFoundException.class)
    public String userNotFoundHandler(HttpServletRequest request, Model model, UserNotFoundException e) {
        model.addAttribute("status", HttpStatus.NOT_FOUND.value());
        model.addAttribute("reason", "Пользователь не найден");
        model.addAttribute("details", request);
        return "errors/error";
    }

    @ExceptionHandler(UserDataCreateException.class)
    public String userDataCreateHandler(HttpServletRequest request, Model model, UserDataCreateException e) {
        model.addAttribute("status", HttpStatus.BAD_REQUEST.value());
        model.addAttribute("reason", "Ошибка создания пользователя: " + e.getMessage());
        model.addAttribute("details", request);
        return "errors/error";
    }

    @ExceptionHandler(SQLException.class)
    public String sqlHandler(HttpServletRequest request, Model model, SQLException e) {
        model.addAttribute("status", HttpStatus.BAD_REQUEST.value());
        model.addAttribute("reason", "Ошибка базы данных: " + e.getMessage());
        model.addAttribute("details", request);
        return "errors/error";
    }

    @ExceptionHandler(Exception.class)
    public String generalHandler(HttpServletRequest request, Model model, Exception e) {
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        model.addAttribute("reason", "Внутренняя ошибка: " + e.getMessage());
        model.addAttribute("details", request);
        return "errors/error";
    }
}
