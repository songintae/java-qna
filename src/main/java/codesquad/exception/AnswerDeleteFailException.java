package codesquad.exception;

public class AnswerDeleteFailException extends RedirectException {
    public AnswerDeleteFailException(){
        super("redirect:/");
    }
}
