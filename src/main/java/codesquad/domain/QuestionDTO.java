package codesquad.domain;

import java.util.List;

public class QuestionDTO {
    private Question question;
    private List<Answer> answers;

    public QuestionDTO() {
    }

    public QuestionDTO(Question question, List<Answer> answers) {
        this.question = question;
        this.answers = answers;
    }

    public Question getQuestion() {
        return question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }



}
