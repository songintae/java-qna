package codesquad.service;

import codesquad.domain.Question;
import codesquad.domain.QuestionRepository;
import codesquad.domain.User;
import codesquad.exception.QuestionDeleteFailException;
import codesquad.exception.QuestionUpdateFailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    public void setQuestionRepository(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Iterable<Question> findAll() {
        return questionRepository.findAll();
    }

    public void save(Question question) {
        if (question.getTitle().isEmpty())
            return;
        questionRepository.save(question);
    }

    public Question findById(Long id) {
        return questionRepository.findById(id).get();
    }

    public void deleteById(Long id, User user) {
        Question question = findById(id);
        if (!question.isOwner(user))
            throw new QuestionDeleteFailException();

        questionRepository.deleteById(id);
    }

    public void updateById(Long id, Question updateQuestion, User user) {
        Question question = findById(id);
        if (!question.updateQuestion(updateQuestion, user))
            throw new QuestionUpdateFailException();

        questionRepository.save(question);
    }
}
