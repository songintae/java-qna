package codesquad.web;

import codesquad.domain.User;
import codesquad.exception.RedirectException;
import codesquad.exception.UserLoginFailException;
import codesquad.exception.UserUpdateFailException;
import codesquad.service.UserService;
import codesquad.util.SessionUtill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "/user/login";
    }

    @PostMapping("/login")
    public String loginUser(String userId, String password, HttpSession session) {
        SessionUtill.setSessionUser(session,userService.login(userId, password));
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logoutUser(HttpSession session) {
        SessionUtill.removeSessionUser(session);
        return "redirect:/";
    }

    @GetMapping("/form")
    public String userForm() {
        return "/user/form";
    }

    @PostMapping("")
    public String create(User user) {
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("")
    public String list(Model model) {
        model.addAttribute("users", userService.findAll());
        return "/user/list";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "/user/profile";
    }

    @GetMapping("/{id}/form")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "/user/updateForm";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, User updated, HttpSession session) {
        User sessionUser = SessionUtill.getSessionUser(session);
        userService.update(id, sessionUser, updated);
        return "redirect:/users";
    }


}
