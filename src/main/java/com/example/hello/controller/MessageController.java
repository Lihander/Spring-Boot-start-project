package com.example.hello.controller;

import com.example.hello.domain.Message;
import com.example.hello.domain.User;
import com.example.hello.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class MessageController {
    private final MessageRepo messageRepo;

    @Autowired
    public MessageController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        model.put("messages", messageRepo.findAll());
        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag,
            Map<String, Object> model){
        Message message = new Message(text, tag, user);
        messageRepo.save(message);

        model.put("messages", messageRepo.findAll());
        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        if (filter == null || filter.isEmpty()) {
            model.put("messages", messageRepo.findAll());
        } else {
            model.put("messages", messageRepo.findByTag(filter));
        }
        return "main";
    }
}
