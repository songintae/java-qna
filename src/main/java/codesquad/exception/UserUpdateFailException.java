package codesquad.exception;

public class UserUpdateFailException extends RedirectException {
    public UserUpdateFailException(){
        super("/user/updateForm_failed");
    }
}
