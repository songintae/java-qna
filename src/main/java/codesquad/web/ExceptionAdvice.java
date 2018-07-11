package codesquad.web;

import codesquad.exception.RedirectException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(RedirectException.class)
    public String redirectException(RedirectException exception){
        return exception.getRedirectUrl();
    }
}
