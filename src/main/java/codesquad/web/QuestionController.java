package codesquad.web;

import codesquad.domain.Answer;
import codesquad.domain.AnswerRepository;
import codesquad.domain.Question;
import codesquad.domain.User;
import codesquad.exception.*;
import codesquad.service.QuestionService;
import codesquad.util.SessionUtill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerRepository answerRepository;


    @GetMapping("/form")
    public String questionForm(HttpSession session) {
        User user = SessionUtill.getSessionUser(session);
        return "/qna/form";
    }

    @PostMapping("")
    public String create(Question question, HttpSession session) {
        User user = SessionUtill.getSessionUser(session);
        question.setWriter(user);
        questionService.save(question);
        return "redirect:/";
    }

    @GetMapping("")
    public String list() {
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model) {
        model.addAttribute("question", questionService.findById(id));
        List<Answer> answers = answerRepository.findAllByQuestionIdAndDeletedFalse(id);
        model.addAttribute("answers", answers);
        model.addAttribute("answerCount", answers.size());
        return "/qna/show";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id, HttpSession session) {
        User user = SessionUtill.getSessionUser(session);
        List<Answer> answers = answerRepository.findAllByQuestionIdAndDeletedFalse(id);
        questionService.deleteById(id, user, answers);
        return "redirect:/";

    }

    @GetMapping("/{id}/update")
    public String updateForm(@PathVariable Long id, Model model) {
        model.addAttribute("question", questionService.findById(id));
        return "/qna/updateForm";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, Question question, HttpSession session) {
        User user = SessionUtill.getSessionUser(session);
        questionService.updateById(id, question, user);
        return "redirect:/questions/" + id;
    }

    @PostMapping("/{questionId}/answer")
    public String registerAnswer(@PathVariable long questionId, Answer answer, HttpSession session){
        User loginUser = SessionUtill.getSessionUser(session);
        answer.setWriter(loginUser);
        answer.setQuestion(questionService.findById(questionId));
        answerRepository.save(answer);
        return "redirect:/questions/"+questionId;
    }

    @DeleteMapping("/{questionId}/answers/{answerId}")
    public String deleteAnswer(@PathVariable long questionId, @PathVariable long answerId, HttpSession session){
        User loginUser = SessionUtill.getSessionUser(session);
        Optional<Answer> mayBeAnswer = answerRepository.findById(answerId);
        mayBeAnswer.filter(answer -> answer.isWriter(loginUser)).orElseThrow(AnswerDeleteFailException::new);
        answerRepository.deleteById(answerId);
        return "redirect:/questions/" + questionId;
    }




}
