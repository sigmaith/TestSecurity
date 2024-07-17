package com.example.testsecurity.controller;

import com.example.testsecurity.dto.JoinDTO;
import com.example.testsecurity.exception.DuplicateUserException;
import com.example.testsecurity.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;

    @GetMapping("/join")
    public String joinP() {

        return "join";
    }

    @PostMapping("/joinProc")
    public String joinProcess(JoinDTO joinDTO, RedirectAttributes redirectAttributes) {
        try{
            joinService.joinProcess(joinDTO);
        } catch (DuplicateUserException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/join";
        }
        return "redirect:/login";
    }
}
