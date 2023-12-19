package edu.hue.jk.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @RequestMapping("top")
    public String top() {
        return "/admin/login/top";
    }

    @RequestMapping("left")
    public String left() {
        return "/admin/login/left";
    }

    @RequestMapping("bottom")
    public String bottom() {
        return "/admin/login/bottom";
    }

    @RequestMapping({"/", "/index"})
    public String loginPage() {
        return "/admin/login/login";
    }

    @RequestMapping("/login")
    public String login() {
        return "/admin/login/home";
    }

    @RequestMapping("welcome")
    public String welcome() {
        return "/admin/login/welcome";
    }

    @RequestMapping("/logout")
    public String logout() {
        return "/admin/login/login";
    }
}
