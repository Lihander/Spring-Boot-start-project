package com.example.hello.controller;

import com.example.hello.domain.Message;
import com.example.hello.domain.User;
import com.example.hello.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/main")
public class MessageController {
    private final MessageRepo messageRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    public MessageController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @GetMapping
    public String main(@RequestParam(required = false, defaultValue = "") String filter,  Model model) {
        if (filter == null || filter.isEmpty()) {
            model.addAttribute("messages", messageRepo.findAll());
        } else {
            model.addAttribute("messages", messageRepo.findByTag(filter));
        }
        model.addAttribute("filter", filter);
        return "main";
    }

    @PostMapping
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag,
            @RequestParam("file") MultipartFile file,
            Map<String, Object> model) throws IOException {
        Message message = new Message(text, tag, user);

        if (file != null) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String fileName = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + fileName));
            message.setFilename(fileName);
        }

        messageRepo.save(message);

        model.put("messages", messageRepo.findAll());
        return "main";
    }
}
