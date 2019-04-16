package com.byhorm.basicbullhorn_1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @Autowired
    MessageRepository msgRepository;

    @RequestMapping("/")
    public String listMsg (Model model) {
        model.addAttribute("messages", msgRepository.findAll());
        return "list";
    }
}
