package com.dev.sav.controller;

import com.dev.sav.model.Appel;
import com.dev.sav.model.Article;
import com.dev.sav.service.AppelService;
import com.dev.sav.service.ArticleService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/appels")
public class AppelController {

    private final AppelService appelService;

    @Autowired
    public AppelController(AppelService appelService) {
        this.appelService = appelService;
    }

    @GetMapping("/{id}")
    public String getAppelById(@PathVariable int id, Model model) {
        Appel appel = appelService.getAppelById(id);
        if (appel != null) {
            model.addAttribute("appel", appel);
            return "client/client";
        } else {
            return "error";
        }
    }

    @GetMapping("/add")
    public String showAppelForm(Model model) {
        model.addAttribute("appel", new Appel());
        return "client/client";
    }

    @PostMapping("/add")
    public String saveAppel(@ModelAttribute Appel appel, HttpServletRequest request) {
        appelService.createAppelWithDossier(appel);
        String clientId = request.getParameter("clientId");
        return "redirect:/client/" + clientId;
    }

    @GetMapping("/edit/{id}")
    public String showEditAppelForm(@PathVariable int id, Model model) {
        Appel appel = appelService.getAppelById(id);
        if (appel != null) {
            model.addAttribute("appel", appel);
            return "client/client";
        } else {
            return "error";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateAppel(@PathVariable int id, @ModelAttribute Appel updatedAppel) {
        appelService.updateAppel(id, updatedAppel);
        return "redirect:/client/client";
    }

    @DeleteMapping("/{id}")
    public String deleteAppel(@PathVariable int id) {
        appelService.deleteAppel(id);
        return "redirect:/client/client";
    }
}
