package com.smashstats.game;

import com.smashstats.Constants;
import com.smashstats.fighters.Fighter;
import com.smashstats.fighters.FighterRepository;
import com.smashstats.stages.Stage;
import com.smashstats.stages.StageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class GameService {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    FighterRepository fighterRepository;

    @Autowired
    StageRepository stageRepository;

    public Iterable<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public void addGame(Game game) {
        gameRepository.save(game);
    }

    public void addGames(List<Game> games) {
        gameRepository.saveAll(games);
    }

    public Game addGame(String winner, String opponent, String stage) {
        Game game = new Game(
                fighterRepository.findByName(winner),
                fighterRepository.findByName(opponent),
                stageRepository.findByName(stage)
        );

        gameRepository.save(game);

        return game;
    }

    public Iterable<Game> addGames(String[] winners, String[] opponents, String[] stages) {

        int numGames = winners.length;

        if(opponents.length != numGames
            || stages.length != numGames) {
            throw new IllegalStateException("Mismatched arrays");
        }

        ArrayList<Game> games = new ArrayList<>(numGames);

        for(int i = 0; i < numGames; i++) {
            Game game = new Game(
                    fighterRepository.findByName(winners[i]),
                    fighterRepository.findByName(opponents[i]),
                    stageRepository.findByName(stages[i])
            );
        }

        gameRepository.saveAll(games);

        return games;
    }

    public Iterable<Game> generateRandomGames(int numGames) {
        ArrayList<Game> randomGames = new ArrayList<>(numGames);
        Random rng = new Random();

        for(int i = 0; i < numGames; i++) {
            Game game = new Game();
            ArrayList<Fighter> fighters = new ArrayList<>(2);
            fighters.add(fighterRepository.findById(rng.nextInt(Constants.NUM_FIGHTERS) + 1).get());
            fighters.add(fighterRepository.findById(rng.nextInt(Constants.NUM_FIGHTERS) + 1).get());
            game.setWinner(fighters.get(0));
            game.setOpponent(fighters.get(1));
            game.setStage(stageRepository.findById(rng.nextInt(Constants.NUM_STAGES) + 1).get());
            randomGames.add(game);
        }

        gameRepository.saveAll(randomGames);

        return randomGames;
    }

    public Iterable<Game> findGamesWithConditions(String winnerName, String opponentName, String stageName) {
        Fighter fighter = fighterRepository.findByName(winnerName);
        Specification<Game> winnerNameSpec = GameSpecifications.WonBy(fighter);

        Fighter opponent = fighterRepository.findByName(opponentName);
        Specification<Game> opponentNameSpec = GameSpecifications.OpponentIs(opponent);

        Stage stage = stageRepository.findByName(stageName);
        Specification<Game> stageSpec = GameSpecifications.OnStage(stage);

        return gameRepository.findAll(Specification.where(winnerNameSpec).and(opponentNameSpec).and(stageSpec));
    }

    public long countGamesWithConditions(String winnerName, String opponentName, String stageName) {
        Fighter fighter = fighterRepository.findByName(winnerName);
        Specification<Game> winnerNameSpec = GameSpecifications.WonBy(fighter);

        Fighter opponent = fighterRepository.findByName(opponentName);
        Specification<Game> opponentNameSpec = GameSpecifications.OpponentIs(opponent);

        Stage stage = stageRepository.findByName(stageName);
        Specification<Game> stageSpec = GameSpecifications.OnStage(stage);

        return gameRepository.count(Specification.where(winnerNameSpec).and(opponentNameSpec).and(stageSpec));
    }

    public Iterable<Game> findAllGamesContainingFighter(String fighterName, String stageName) {
        Fighter fighter = fighterRepository.findByName(fighterName);
        Specification<Game> fighterNameSpec = GameSpecifications.HasFighter(fighter);

        Stage stage = stageRepository.findByName(stageName);
        Specification<Game> stageSpec = GameSpecifications.OnStage(stage);

        return gameRepository.findAll(Specification.where(fighterNameSpec).and(stageSpec));
    }

    public Long countAllGamesContainingFighter(String fighterName, String stageName) {
        Fighter fighter = fighterRepository.findByName(fighterName);
        Specification<Game> fighterNameSpec = GameSpecifications.HasFighter(fighter);

        Stage stage = stageRepository.findByName(stageName);
        Specification<Game> stageSpec = GameSpecifications.OnStage(stage);

        return gameRepository.count(Specification.where(fighterNameSpec).and(stageSpec));
    }

}
