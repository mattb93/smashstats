package com.smashstats.fighters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/fighters")
public class FighterController {

    @Autowired
    private FighterRepository fighterRepository;

    @GetMapping("")
    public String getAllFighters(Model model) {
        Iterable fighters = fighterRepository.findAll();

        model.addAttribute("fighters", fighters);

        return "fighters";
    }

    @GetMapping("/getAll")
    public @ResponseBody Iterable<Fighter> getAllFighters() {
        return fighterRepository.findAll();
    }

}
