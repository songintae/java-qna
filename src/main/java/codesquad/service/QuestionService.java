package codesquad.service;

import codesquad.domain.Answer;
import codesquad.domain.Question;
import codesquad.domain.QuestionRepository;
import codesquad.domain.User;
import codesquad.exception.QuestionDeleteFailException;
import codesquad.exception.QuestionUpdateFailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Question> findAllByDeletedFalse(){
        return questionRepository.findAllByDeletedFalse();
    };

    public void save(Question question) {
        if (question.getTitle().isEmpty())
            return;
        questionRepository.save(question);
    }

    public Question findById(Long id) {
        return questionRepository.findById(id).get();
    }

    public void deleteById(Long id, User user, List<Answer> answers) {
        Question question = findById(id);
        if(!question.isDeleteQuestion(user,answers))
            throw new QuestionDeleteFailException();
        question.setDeleted();
        save(question);
    }

    public void updateById(Long id, Question updateQuestion, User user) {
        Question question = findById(id);
        if (!question.updateQuestion(updateQuestion, user))
            throw new QuestionUpdateFailException();

        questionRepository.save(question);
    }
}
