package com.smashstats.stages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/stages")
public class StageController {

    @Autowired
    private StageRepository stageRepository;

    @GetMapping("/all")
    public @ResponseBody Iterable<Stage> getAllStages() {
        return stageRepository.findAll();
    }
}
