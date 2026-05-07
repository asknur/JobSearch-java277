package kg.attractor.jobsearchjava27.config;


import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.jobsearchjava27.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@ControllerAdvice
@RequiredArgsConstructor
public class LocalAdvice {

    private final UserRepository userRepository;

    @ModelAttribute
    public void persistLocaleIfChanged(HttpServletRequest request, Authentication auth) {
        if (request.getParameter("lang") == null) return;
        if (auth == null || !auth.isAuthenticated()) return;
        if ("anonymousUser".equals(auth.getPrincipal())) return;

        Locale locale = LocaleContextHolder.getLocale();
        userRepository.findByEmail(auth.getName()).ifPresent(user -> {
            user.setLocale(locale.toLanguageTag());
            userRepository.save(user);
        });
    }
}
