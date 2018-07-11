package codesquad.exception;

public class UserLoginFailException extends RedirectException {
    public UserLoginFailException(){
        super("/user/login_failed");
    }
}
