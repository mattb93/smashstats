package com.smashstats.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/games")
public class GameController {

    @Autowired
    GameService gameService;

    @GetMapping("/all")
    public @ResponseBody Iterable<Game> getAllGames() {
        return gameService.getAllGames();
    }

    @GetMapping("/addNew")
    public @ResponseBody Game addGame(
            @RequestParam(value="winner") String winner,
            @RequestParam(value="opponent") String opponent,
            @RequestParam(value="stage") String stage) {

        return gameService.addGame(winner, opponent, stage);
    }

    @GetMapping("/genRandoms")
    public @ResponseBody Iterable<Game> generateRandomGames(@RequestParam(value="numGames", required=false, defaultValue = "100") int numGames) {

        return gameService.generateRandomGames(numGames);
    }

    @GetMapping("/{fighterName}")
    public @ResponseBody Iterable<Game> findGames(
            @RequestParam(value="winner", required=false) String winner,
            @RequestParam(value="opponent", required=false) String opponent,
            @RequestParam(value="stage", required=false) String stageName) {

        return gameService.findGamesWithConditions(winner, opponent, stageName);
    }
}
