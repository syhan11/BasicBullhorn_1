package com.byhorm.basicbullhorn_1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    MessageRepository msgRepository;

    @RequestMapping("/")
    public String listMsg (Model model) {
        model.addAttribute("messages", msgRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String msgForm(Model model){
        model.addAttribute("message", new Message());
        return "msgform";
    }

    @PostMapping("/process")
    public String processMsg(@Valid Message message,
                             BindingResult result) {
        if (result.hasErrors()) {
            return "msgform";
        }
        msgRepository.save(message);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showMsg(@PathVariable("id") long id, Model model) {
        model.addAttribute("message", msgRepository.findById(id).get());
        return "show";
    }
}
