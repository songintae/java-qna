package codesquad.web;

import codesquad.domain.Question;
import codesquad.domain.User;
import codesquad.exception.QuestionDeleteFailException;
import codesquad.exception.QuestionUpdateFailException;
import codesquad.exception.RedirectException;
import codesquad.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;


    @GetMapping("/form")
    public String questionForm(HttpSession session) {
        User user = UserController.getSessionUser(session);
        if (user == null)
            return "redirect:/users/login";
        return "/qna/form";
    }

    @PostMapping("")
    public String create(Question question, HttpSession session) {
        User user = UserController.getSessionUser(session);
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
        return "/qna/show";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id, HttpSession session) {
        User user = UserController.getSessionUser(session);
        questionService.deleteById(id, user);
        return "redirect:/";

    }

    @GetMapping("/{id}/update")
    public String updateForm(@PathVariable Long id, Model model) {
        model.addAttribute("question", questionService.findById(id));
        return "/qna/updateForm";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, Question question, HttpSession session) {
        User user = UserController.getSessionUser(session);
        questionService.updateById(id, question, user);
        return "redirect:/questions/" + id;
    }



}
