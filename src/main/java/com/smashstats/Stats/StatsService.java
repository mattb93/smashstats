package com.smashstats.Stats;

import com.smashstats.Constants;
import com.smashstats.fighters.Fighter;
import com.smashstats.fighters.FighterRepository;
import com.smashstats.game.Game;
import com.smashstats.game.GameRepository;
import com.smashstats.stages.StageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatsService {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    FighterRepository fighterRepository;

    @Autowired
    StageRepository stageRepository;

    public Iterable<Matchup> allMatchups(String fighterName) {
        Fighter winner = fighterRepository.findByName(fighterName);

        Specification<Game> countAllWinsSpec = StatSpecifications.CountAllWins(winner);

        System.out.println(winner.getName());

        List<Object[]> wins = gameRepository.countWinsGroupByOpponent(winner.getId());
        List<Object[]> losses = gameRepository.countLossesGroupByOpponent(winner.getId());

        Map<Integer, Matchup> matchupOrganizer = new HashMap<>();
        for(Fighter opponent : fighterRepository.findAll()) {
            matchupOrganizer.put(opponent.getId(), new Matchup(winner, opponent));
        }

        for(int i = 0; i < wins.size(); i++) {
            matchupOrganizer.get(((Fighter)wins.get(i)[0]).getId()).setWins(((Long)wins.get(i)[1]));
        }

        for(int i = 0; i < losses.size(); i++) {
            matchupOrganizer.get(((Fighter)losses.get(i)[0]).getId()).setLosses(((Long)losses.get(i)[1]));
        }

        return matchupOrganizer.values();
    }
}
