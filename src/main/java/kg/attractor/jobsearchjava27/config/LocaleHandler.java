package kg.attractor.jobsearchjava27.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kg.attractor.jobsearchjava27.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;

import java.io.IOException;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class LocaleHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final LocaleResolver localeResolver;
    private final UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        userRepository.findByEmail(authentication.getName()).ifPresent(user -> {
            if (user.getLocale() != null && !user.getLocale().isBlank()) {
                Locale locale = Locale.forLanguageTag(user.getLocale().replace('_', '-'));
                localeResolver.setLocale(request, response, locale);
            }
        });
        setDefaultTargetUrl("/");
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
