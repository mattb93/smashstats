package com.smashstats.Stats;

import com.smashstats.game.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/stats")
public class StatsController {

    @Autowired
    GameService gameService;

    @Autowired
    StatsService statsService;

    @GetMapping("/{fighterName}")
    public String getStats(
            @PathVariable(value = "fighterName") String fighterName,
            @RequestParam(value = "opponentName", required=false) String opponentName,
            @RequestParam(value = "stage", required = false) String stageName,
            Model model) {

        long totalGames = gameService.countAllGamesContainingFighter(fighterName, stageName);
        long wins = gameService.countGamesWithConditions(fighterName, null, stageName);

        if(opponentName == null) {
            opponentName = "all other fighters";
        }

        String result = fighterName + " has won " + wins + " out of " + totalGames + " games against " + opponentName;

        if(stageName != null) {
            result += " on " + stageName;
        }

        model.addAttribute("fighterName", fighterName);
        model.addAttribute("totalGames", totalGames);
        model.addAttribute("wins", wins);

        model.addAttribute("matchups", statsService.allMatchups(fighterName));

        return "stats";
    }
}
