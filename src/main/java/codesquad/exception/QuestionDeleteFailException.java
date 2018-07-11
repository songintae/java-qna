package codesquad.exception;

public class QuestionDeleteFailException extends RedirectException {
    public QuestionDeleteFailException() {
        super("redirect:/users/login");
    }
}
