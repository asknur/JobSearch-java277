package kg.attractor.jobsearchjava27.service;

import kg.attractor.jobsearchjava27.exception.handler.ErrorResponseBody;
import org.springframework.validation.BindingResult;

public interface ErrorService {
    ErrorResponseBody makeResponse(Exception e, String exceptionClass);

    ErrorResponseBody makeResponse(BindingResult bindingResult);

}
