package codesquad.exception;

public class UserNotLoginException extends RedirectException {
    public UserNotLoginException() {
        super("/user/form");
    }
}
