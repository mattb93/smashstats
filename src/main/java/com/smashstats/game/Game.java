package com.smashstats.game;

import com.smashstats.fighters.Fighter;
import com.smashstats.stages.Stage;

import javax.persistence.*;

@Entity
@Table(name = "Games")
public class Game {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Fighter winner;

    @ManyToOne
    private Fighter opponent;

    @ManyToOne
    private Stage stage;

    public Game() {}
    public Game(Fighter winner, Fighter opponent, Stage stage) {
        this.winner = winner;
        this.opponent = opponent;
        this.stage = stage;
    }

    public Integer getId() { return this.id; }

    public void setId(Integer id) { this.id = id; }


    public Fighter getWinner() {
        return winner;
    }

    public void setWinner(Fighter winner) { this.winner = winner; }


    public Fighter getOpponent() {
        return opponent;
    }

    public void setOpponent(Fighter opponent) {
        this.opponent = opponent;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) { this.stage = stage; }


    public boolean hasFighter(Fighter fighter) {
        return winner.equals(fighter) || opponent.equals(fighter);
    }

    public boolean isOnStage(Stage stage) {
        return this.getStage().equals(stage);
    }
}
