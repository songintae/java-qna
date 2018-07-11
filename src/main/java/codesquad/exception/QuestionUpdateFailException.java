package codesquad.exception;

public class QuestionUpdateFailException extends RedirectException {
    public QuestionUpdateFailException() {
        super("/qna/updateForm_failed");
    }
}
