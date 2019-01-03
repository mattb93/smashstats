package com.smashstats.Stats;

import com.smashstats.fighters.Fighter;
import com.smashstats.stages.Stage;

public class Matchup {

    private Fighter winner;
    private Fighter opponent;
    private Stage stage;
    private Long wins = 0L;
    private Long losses = 0L;

    public Matchup(Fighter winner, Fighter opponent) {
        this.winner = winner;
        this.opponent = opponent;
    }

    public Fighter getWinner() {
        return winner;
    }

    public void setWinner(Fighter winner) {
        this.winner = winner;
    }

    public Fighter getOpponent() {
        return opponent;
    }

    public void setOpponent(Fighter opponent) {
        this.opponent = opponent;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Long getWins() {
        return wins;
    }

    public void setWins(Long wins) {
        this.wins = wins;
    }

    public Long getLosses() {
        return losses;
    }

    public void setLosses(Long losses) {
        this.losses = losses;
    }
}
